package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;

public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Integer>{
    List<UserProgressEntity> findByUserAndTopicIn(UserEntity user, List<TopicEntity> topics);
    long countByUserAndTopicLessonAndCompleted(UserEntity user, LessonEntity lesson, boolean completed);
    UserProgressEntity findByUserAndTopic(UserEntity user, TopicEntity topic);
    int countDistinctUserByTopicIn(List<TopicEntity> topics);
    int countDistinctUserByTopic(TopicEntity topic);
    int countByTopicAndCompleted(TopicEntity topic, boolean completed);
    @Query("SELECT up.user, COUNT(up) FROM UserProgressEntity up WHERE up.completed = true GROUP BY up.user ORDER BY COUNT(up) DESC")
    List<Object[]> findTopUsersByCompletedTopics();
    int countByUserUidAndCompleted(String userId, boolean completed);
    List<UserProgressEntity> findTop2ByUserUidOrderByLastViewedDesc(String userId);
    List<UserProgressEntity> findTopByUserUidOrderByUserViewCountDesc(String userId);
    @Query("SELECT up.user, "
        + "COUNT(DISTINCT up.topic) AS topicCount, "
        + "COUNT(DISTINCT up.topic.lesson) AS lessonCount "
        + "FROM UserProgressEntity up "
        + "WHERE up.completed = true "
        + "AND up.user.userType = 'Student' " 
        + "GROUP BY up.user "
        + "ORDER BY lessonCount DESC, topicCount DESC")
    List<Object[]> findTopUsersByCompletedLessonsAndTopics();
    
}
