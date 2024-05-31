package com.IT334G4.Mathminds.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.LessonQuizEntity;
import com.IT334G4.Mathminds.Service.LessonQuizService;

@RestController
@RequestMapping("mathminds/lessonQuiz")
@CrossOrigin
public class LessonQuizController {
    @Autowired
    LessonQuizService lessonQuizService;

    @GetMapping("print")
    public String printHello(){
        return "Hello, LessonQuiz Controller working!!!";
    }

    @PostMapping("/insertLessonQuiz")
    public LessonQuizEntity insertLesson(@RequestBody LessonQuizEntity lessonQuiz){
        return lessonQuizService.insertLessonQuiz(lessonQuiz);
    }

    @GetMapping("/getAllLessonsQuiz")
    public List<LessonQuizEntity> getAllLessonsQuiz(){
        return lessonQuizService.getAllLessonsQuiz();
    }

    @PutMapping("/updateLessonQuiz")
    public LessonQuizEntity updateLessonQuiz(@RequestParam int lessonQuizId, @RequestBody LessonQuizEntity newLessonDetails){
        return lessonQuizService.updateLessonQuiz(lessonQuizId, newLessonDetails);
    }

    @DeleteMapping("/deleteLessonQuiz/{lessonQuizId}")
    public String deleteLesson(@PathVariable int lessonQuizId){
        return lessonQuizService.deleteLessonQuiz(lessonQuizId);
    }
}
