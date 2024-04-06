package com.IT334G4.Mathminds.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IT334G4.Mathminds.Entity.LessonEntity;

public interface LessonRepository extends JpaRepository<LessonEntity,Integer> {

}
