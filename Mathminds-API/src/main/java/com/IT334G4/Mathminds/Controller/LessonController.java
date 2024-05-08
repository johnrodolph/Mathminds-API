package com.IT334G4.Mathminds.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Service.LessonService;

@RestController
@RequestMapping("mathminds/lesson")
@CrossOrigin
public class LessonController {
    @Autowired
    LessonService lessonService;

    @GetMapping("print")
    public String printHello(){
        return "Hello, Lesson Controller working!!!";
    }

    @PostMapping("/insertLesson")
    public LessonEntity insertLesson(@RequestBody LessonEntity lesson){
        return lessonService.insertLesson(lesson);
    }

    @GetMapping("/getAllLessons")
    public List<LessonEntity> getAllLessons(){
        return lessonService.getAllLessons();
    }
}
