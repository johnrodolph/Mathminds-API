package com.IT334G4.Mathminds.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.IT334G4.Mathminds.Entity.PracticeEntity;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntity, Integer>{

    @Modifying
    @Transactional
    @Query("DELETE FROM PracticeEntity p WHERE p.topic.topicId = :topicId")
    void deleteByTopicTopicId(int topicId);

    List<PracticeEntity> findByTopicTopicId(int topicId);

    @Query("SELECT p FROM PracticeEntity p ORDER BY p.practiceViewCount DESC")
    List<PracticeEntity> findTop3ByOrderByPracticeViewCountDesc();

}
