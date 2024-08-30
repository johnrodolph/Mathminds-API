package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.UserBadgeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadgeEntity, Integer>{
    boolean existsByUserAndLesson(UserEntity user, LessonEntity lesson);
    List<UserBadgeEntity> findByUser(UserEntity user);
}
