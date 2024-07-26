package com.IT334G4.Mathminds.Controller;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/updateLesson")
    public LessonEntity updateLesson(@RequestParam int lessonId, @RequestBody LessonEntity newLessonDetails){
        return lessonService.updateLesson(lessonId, newLessonDetails);
    }

    @DeleteMapping("/deleteLesson/{lessonId}")
    public String deleteLesson(@PathVariable int lessonId){
        return lessonService.deleteLesson(lessonId);
    }

    @GetMapping("/getLessonById")
    public LessonEntity getLessonById(@RequestParam int lessonId){
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping("/lessonWithProgress/{lessonId}")
    public Map<String, Object> getLessonWithProgress(@PathVariable int lessonId, @RequestParam String uid) {
        return lessonService.getLessonWithProgress(lessonId, uid);
    }
}
