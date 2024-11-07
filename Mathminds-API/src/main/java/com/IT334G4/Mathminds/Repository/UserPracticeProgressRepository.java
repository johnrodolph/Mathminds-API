package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserPracticeProgressEntity;

public interface UserPracticeProgressRepository extends JpaRepository<UserPracticeProgressEntity, Integer> {
    long countByUserAndPractice(UserEntity user, PracticeEntity practice); // Count practices completed by user

    List<UserPracticeProgressEntity> findByUserOrderByTimestampDesc(UserEntity user); // Find user's practice sessions sorted by timestamp

    List<UserPracticeProgressEntity> findTop2ByUserUidOrderByLastViewedDesc(String userId);
    
    List<UserPracticeProgressEntity> findTop5ByUserOrderByViewCountDesc(UserEntity user); // Top 5 most accessed practices for user

    UserPracticeProgressEntity findByUserAndPractice(UserEntity user, PracticeEntity practice);

    //List<UserPracticeProgressEntity> findByUserOrderByTimestampDesc(String userId);

    //List<UserPracticeProgressEntity> findTop5ByUserOrderByViewCountDesc(String userId);
}
