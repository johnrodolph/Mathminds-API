package com.IT334G4.Mathminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IT334G4.Mathminds.Entity.LessonEntity;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,Integer> {

}
