package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.UserBadgeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;

@Repository
public interface UserBadgeRepository extends JpaRepository<UserBadgeEntity, Integer>{
    boolean existsByUserAndLesson(UserEntity user, LessonEntity lesson);
    List<UserBadgeEntity> findByUser(UserEntity user);
    long countByLesson(LessonEntity lesson);
    int countByUserUid(String userId);
    @Query("SELECT ub.user, COUNT(ub) FROM UserBadgeEntity ub WHERE ub.user.userType = 'Student' GROUP BY ub.user ORDER BY COUNT(ub) DESC")
    List<Object[]> findTopUsersByBadgeCount();
}
