package com.IT334G4.Mathminds.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
// import com.IT334G4.Mathminds.Entity.UserProgressEntity;
import com.IT334G4.Mathminds.Repository.LessonRepository;
import com.IT334G4.Mathminds.Repository.TopicRepository;
import com.IT334G4.Mathminds.Repository.UserBadgeRepository;
import com.IT334G4.Mathminds.Repository.UserProgressRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;

@Service
public class AnalyticsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    // Unused
    // public Map<String, Object> getAllAnalytics(String uid) {
    //     Map<String, Object> analyticsData = new HashMap<>();
    //     // analyticsData.put("userTopicsCompleted", userProgressRepository.countByUserUidAndCompleted(uid, true));
    //     // analyticsData.put("userRecentTopicViewed", getUserRecentTopicViewed(uid));
    //     // analyticsData.put("userBadgeCount", userBadgeRepository.countByUserUid(uid));
    //     // analyticsData.put("userMostAccessedContent", getUserMostAccessedContent(uid));

    //     // Get all analytics data
    //     // analyticsData.put("recentLessons", getRecentLessons());
    //     // analyticsData.put("recentTopics", getRecentTopicTitles());
    //     // analyticsData.put("totalStudents", userRepository.countByUserType("Student"));
    //     // analyticsData.put("totalLessons", lessonRepository.count());
    //     // analyticsData.put("totalTopics", topicRepository.count());
    //     // analyticsData.put("totalTopicViewCounts", getTopicViewCounts());
    //     // analyticsData.put("totalPracticeActivities", practiceRepository.count());
    //     // analyticsData.put("totalBadgesAwarded", userBadgeRepository.count());
    //     // analyticsData.put("badgeCountPerLesson", getBadgeCountPerLesson());
    //     // analyticsData.put("totalLessonViewCounts", getLessonViewCounts());
    //     // analyticsData.put("totalLessonUniqueUserCounts",getLessonUniqueUserCounts());
    //     // analyticsData.put("completionAnalytics", getViewsToCompletionAnalytics());
    //     // analyticsData.put("topUsersByCompletedTopics", getTopUsersByCompletedTopics());

    //     return analyticsData;
    // }

    // For dashboard overview content
    public Map<String, Object> getOverviewDashboardContent(String uid) {
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
        analyticsData.put("totalStudents", userRepository.countByUserType("Student"));
        analyticsData.put("totalLessons", lessonRepository.count());
        analyticsData.put("totalTopics", topicRepository.count());
        analyticsData.put("recentLessons", getRecentLessons());
        analyticsData.put("recentTopics", getRecentTopicTitles());
        analyticsData.put("totalBadgesAwarded", userBadgeRepository.count());

        return analyticsData;
    }

    public Map<String, Object> getLessonDashboardContent(String uid) {
        
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
        analyticsData.put("badgeCountPerLesson", getBadgeCountPerLesson());
        analyticsData.put("totalLessons", lessonRepository.count());
        analyticsData.put("totalLessonViewCounts", getLessonViewCounts());
        analyticsData.put("uniqueLessonViewCounts", getLessonUniqueUserCounts());

        return analyticsData;
    }

    public Map<String, Object> getTopicDashboardContent(String uid) {
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
        analyticsData.put("recentTopics", getRecentTopicTitles());
        analyticsData.put("totalTopics", topicRepository.count());
        analyticsData.put("totalTopicViewCounts", getTopicViewCounts());
        analyticsData.put("completionAnalytics", getViewsToCompletionAnalytics());      

        return analyticsData;
    }

    // Fetches the recently added lessons
    private List<Map<String, Object>> getRecentLessons() {
        List<LessonEntity> recentLessons = lessonRepository.findTop2ByOrderByLessonIdDesc();
        return recentLessons.stream()
            .map(lesson -> {
                Map<String, Object> lessonData = new HashMap<>();
                lessonData.put("lessonTitle", lesson.getLessonTitle());
                lessonData.put("lessonDateAdded", lesson.getLessonDateAdded());
                return lessonData;
            })
            .collect(Collectors.toList());
    }

    // Separate method to get recent topic titles
    private List<Map<String, Object>> getRecentTopicTitles() {
        List<TopicEntity> recentTopics = topicRepository.findTop2ByOrderByTopicIdDesc();
        return recentTopics.stream().map(topic -> {
            Map<String, Object> topicData = new HashMap<>();
            topicData.put("topicTitle", topic.getTopicTitle());
            topicData.put("topicDateAdded", topic.getTopicDateAdded());
            return topicData;
        })
        .collect(Collectors.toList());
    }

    // Fetches the amount of users who earned under each lesson
    private Map<String, Long> getBadgeCountPerLesson(){
        List<LessonEntity> allLessons = lessonRepository.findAll();

        return allLessons.stream().collect(Collectors.toMap(
            LessonEntity::getLessonTitle,
            lesson -> userBadgeRepository.countByLesson(lesson)
            ));
    }

    // Fetches the total/cumulative view count of each lessons
    private Map<String, Long> getLessonViewCounts() {
        List<LessonEntity> allLessons = lessonRepository.findAll();

        return allLessons.stream()
            .collect(Collectors.toMap(
                LessonEntity::getLessonTitle,
                lesson -> lesson.getLessonTopics().stream().mapToLong(TopicEntity::getTopicViewCount).sum()

            ));
    }
    
    // Fetches the individual users who viewed a lesson (not cumulative)
    private Map<String, Object> getLessonUniqueUserCounts() {
        List<LessonEntity> allLessons = lessonRepository.findAll();
        
        return allLessons.stream()
            .collect(Collectors.toMap(
                LessonEntity::getLessonTitle,
                lesson -> {
                    // Get all topics for this lesson
                    List<TopicEntity> lessonTopics = lesson.getLessonTopics();
                    
                    // Count distinct users who have accessed any topic in this lesson
                    return userProgressRepository.countDistinctUserByTopicIn(lessonTopics);
                }
            ));
    }

    // For checking unique topic views to completion ratio
    public Map<String, Map<String, Map<String, Integer>>> getViewsToCompletionAnalytics() {
        List<LessonEntity> allLessons = lessonRepository.findAll();
        
        return allLessons.stream()
            .collect(Collectors.toMap(
                LessonEntity::getLessonTitle,
                lesson -> getTopicCompletionStats(lesson.getLessonTopics())
            ));
    }

    // Utilized in getViewsToCompletionAnalytics() for getting the unique and total views of a topic and the numbers of users who completed the topic
    private Map<String, Map<String, Integer>> getTopicCompletionStats(List<TopicEntity> topics) {
        return topics.stream()
            .collect(Collectors.toMap(
                TopicEntity::getTopicTitle,
                topic -> {
                    Map<String, Integer> stats = new HashMap<>();
                    stats.put("uniqueTotalView", userProgressRepository.countDistinctUserByTopic(topic));
                    stats.put("numberOfUsersCompleted", userProgressRepository.countByTopicAndCompleted(topic, true));
                    stats.put("totalViewCount", (int) topic.getTopicViewCount());
                    return stats;
                }
            ));
    }

    // For fetching view counts for each topic
    private Map<String, Long> getTopicViewCounts() {
        List<TopicEntity> allTopic= topicRepository.findAll();

        return allTopic.stream()
            .collect(Collectors.toMap(
                TopicEntity::getTopicTitle,
                topic -> topic.getTopicViewCount()
            ));
    }

    // Fetches the top users with the most topics completed (Potential use for leaderboard)
    // private List<Map<String, Object>> getTopUsersByCompletedTopics() {
    //     List<Object[]> results = userProgressRepository.findTopUsersByCompletedTopics();
    
    //     return results.stream().limit(5).map(result -> {
    //         UserEntity user = (UserEntity) result[0];
    //         Long completedCount = (Long) result[1];
    //         Map<String, Object> userStats = new HashMap<>();
    //         userStats.put("fullName", user.getFname() + " " + user.getLname());
    //         userStats.put("completedTopicCount", completedCount);
    //         return userStats;
    //     }).collect(Collectors.toList());
    // }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public class UnauthorizedAccessException extends RuntimeException {
        public UnauthorizedAccessException(String message) {
            super(message);
        }
    }
    
    
}

