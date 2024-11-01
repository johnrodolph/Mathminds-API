package com.IT334G4.Mathminds.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;
import com.IT334G4.Mathminds.Repository.UserBadgeRepository;
import com.IT334G4.Mathminds.Repository.UserProgressRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;

@Service
public class UserAnalyticsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;
    public Map<String, Object> getIndividualUserDashboardData(String uid) {
        UserEntity user = new UserEntity();
        user = userRepository.findById(uid).get();
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String userType = user.getUserType();
        if (!("Teacher".equals(userType) || "Admin".equals(userType))) {
            // Return limited data for unauthorized users
            throw new UnauthorizedAccessException("Access denied.");
        }
        Map<String, Object> analyticsData = new HashMap<>();
        analyticsData.put("userTopicsCompleted", userProgressRepository.countByUserUidAndCompleted(uid, true));
        analyticsData.put("userRecentTopicViewed", getUserRecentTopicViewed(uid));
        analyticsData.put("userBadgeCount", userBadgeRepository.countByUserUid(uid));
        analyticsData.put("userMostAccessedContent", getUserMostAccessedContent(uid));

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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }
}
