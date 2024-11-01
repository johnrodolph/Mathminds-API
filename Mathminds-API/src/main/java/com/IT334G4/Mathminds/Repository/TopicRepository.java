package com.IT334G4.Mathminds.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IT334G4.Mathminds.Entity.TopicEntity;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Integer>{
    List<TopicEntity> findTop2ByOrderByTopicIdDesc();
}
