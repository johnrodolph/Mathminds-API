package com.IT334G4.Mathminds.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;
import com.IT334G4.Mathminds.Entity.UserPracticeProgressEntity;
import com.IT334G4.Mathminds.Repository.PracticeRepository;
import com.IT334G4.Mathminds.Repository.UserBadgeRepository;
import com.IT334G4.Mathminds.Repository.UserProgressRepository;
import com.IT334G4.Mathminds.Repository.UserPracticeProgressRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;
import com.IT334G4.Mathminds.Repository.LessonRepository;
import com.IT334G4.Mathminds.Repository.TopicRepository;

@Service
public class UserAnalyticsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private UserPracticeProgressRepository userPracticeProgressRepository;

    @Autowired
    private PracticeRepository practiceRepo;

    @Autowired
    private LessonRepository lessonRepo;

    @Autowired
    private TopicRepository topicRepo;

    public Map<String, Object> getIndividualUserDashboardData(String uid) {
        UserEntity user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!"Student".equals(user.getUserType())) {
            throw new UnauthorizedAccessException("Access denied.");
        }

        Map<String, Object> analyticsData = new HashMap<>();
        analyticsData.put("userTopicsCompleted", userProgressRepository.countByUserUidAndCompleted(uid, true));
        analyticsData.put("userRecentTopicViewed", getUserRecentTopicViewed(uid));
        analyticsData.put("userBadgeCount", userBadgeRepository.countByUserUid(uid));
        analyticsData.put("userMostAccessedContent", getUserMostAccessedContent(uid));
        analyticsData.put("userRecentPracticeViewed", getUserRecentPracticeViewed(uid));
        analyticsData.put("userMostAccessedPractice", getUserMostAccessedPractice(user));
        analyticsData.put("totalLessons", lessonRepo.count());
        analyticsData.put("totalTopics", topicRepo.count());

        return analyticsData;
    }

    public List<Map<String, Object>> getUserRecentTopicViewed(String userId) {
        List<UserProgressEntity> recentTopics = userProgressRepository.findTop2ByUserUidOrderByLastViewedDesc(userId);

        return recentTopics.stream().map(progress -> {
            Map<String, Object> topicData = new HashMap<>();
            topicData.put("topicTitle", progress.getTopic().getTopicTitle());
            topicData.put("lastViewed", progress.getLastViewed());
            topicData.put("lessonTitle", progress.getTopic().getLesson().getLessonTitle());
            return topicData;
        }).collect(Collectors.toList());
    }

    public Map<String, Object> getUserMostAccessedContent(String userId) {
        List<UserProgressEntity> mostAccessed = userProgressRepository.findTopByUserUidOrderByUserViewCountDesc(userId);

        if (mostAccessed.isEmpty()) {
            return null; // No accessed topics found for user
        }

        UserProgressEntity topTopicProgress = mostAccessed.get(0);
        Map<String, Object> topicData = new HashMap<>();
        topicData.put("topicTitle", topTopicProgress.getTopic().getTopicTitle());
        topicData.put("viewCount", topTopicProgress.getUserViewCount());
        topicData.put("lessonTitle", topTopicProgress.getTopic().getLesson().getLessonTitle());

        return topicData;
    }

    public void trackPracticeView(UserEntity user, PracticeEntity practice) {
        UserPracticeProgressEntity progress = userPracticeProgressRepository.findByUserAndPractice(user, practice);
        LocalDateTime now = LocalDateTime.now();

        if(progress == null){
            // First time user is viewing this topic
            progress = new UserPracticeProgressEntity(user, practice);
            progress.setFirstViewed(now);
            progress.setViewCount(1);
        }else{
            // User has viewed this topic before
            progress.setViewCount(progress.getViewCount() + 1);
        }

        progress.setLastViewed(now);
        userPracticeProgressRepository.save(progress);

        // Update total view count on practice
        practice.setPracticeViewCount(practice.getPracticeViewCount() + 1);
        practiceRepo.save(practice);
    }

    public List<Map<String, Object>> getUserRecentPracticeViewed(String userId) {
        List<UserPracticeProgressEntity> recentPractices = userPracticeProgressRepository.findTop2ByUserUidOrderByLastViewedDesc(userId);
    
        return recentPractices.stream().limit(2).map(progress -> {
            Map<String, Object> practiceData = new HashMap<>();
            PracticeEntity practice = progress.getPractice();
            
            practiceData.put("practiceId", practice.getPracticeId());
            practiceData.put("lastViewed", progress.getLastViewed());
            
            // Retrieve nested data
            practiceData.put("topicTitle", practice.getTopic().getTopicTitle());
            practiceData.put("lessonTitle", practice.getTopic().getLesson().getLessonTitle());
            
            return practiceData;
        }).collect(Collectors.toList());
    }
    
    public Map<String, Object> getUserMostAccessedPractice(UserEntity user) {
        List<UserPracticeProgressEntity> mostAccessedPractices = userPracticeProgressRepository.findTop5ByUserOrderByViewCountDesc(user);
    
        if (mostAccessedPractices.isEmpty()) {
            return new HashMap<>();
        }
    
        UserPracticeProgressEntity topPracticeProgress = mostAccessedPractices.get(0);
        PracticeEntity practice = topPracticeProgress.getPractice();
        
        Map<String, Object> practiceData = new HashMap<>();
        practiceData.put("practiceId", practice.getPracticeId());
        practiceData.put("viewCount", topPracticeProgress.getViewCount());
        
        // Retrieve nested data
        practiceData.put("topicTitle", practice.getTopic().getTopicTitle());
        practiceData.put("lessonTitle", practice.getTopic().getLesson().getLessonTitle());
        
        return practiceData;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }
}
