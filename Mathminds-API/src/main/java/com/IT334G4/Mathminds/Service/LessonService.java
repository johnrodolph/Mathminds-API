package com.IT334G4.Mathminds.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Repository.LessonRepository;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepo;

    public LessonEntity insertLesson(LessonEntity lesson){
        return lessonRepo.save(lesson);
    }

    public List<LessonEntity> getAllLessons(){
        return lessonRepo.findAll();
    }
}
