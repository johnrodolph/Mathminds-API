package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;

public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Integer>{
    List<UserProgressEntity> findByUserAndTopicIn(UserEntity user, List<TopicEntity> topics);
    long countByUserAndTopicLessonAndCompleted(UserEntity user, LessonEntity lesson, boolean completed);
    UserProgressEntity findByUserAndTopic(UserEntity user, TopicEntity topic);
}
