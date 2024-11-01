package com.IT334G4.Mathminds.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;
import com.IT334G4.Mathminds.Repository.LessonRepository;
import com.IT334G4.Mathminds.Repository.TopicRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;
import com.IT334G4.Mathminds.Service.UserProgressService;

@RestController
@RequestMapping("mathminds/userProgress")
@CrossOrigin
public class UserProgressController {
    @Autowired
    UserProgressService userProgressService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LessonRepository lessonRepo;

    @Autowired
    private TopicRepository topicRepo;

    @GetMapping("print")
    public String printHello(){
        return "Hello, User Progress Controller working!!!";
    }

    @GetMapping("/lessonProgress/{uid}/{lessonId}")
    public double getLessonProgress(@PathVariable String uid, @PathVariable int lessonId) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        LessonEntity lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new RuntimeException("Lesson not found"));
        return userProgressService.calculateLessonProgress(user, lesson);
    }

    @PostMapping("/updateProgress")
    public void updateProgress(@RequestParam String uid, @RequestParam int topicId, @RequestParam boolean completed) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        TopicEntity topic = topicRepo.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        userProgressService.updateProgress(user, topic, completed);
    }

    @GetMapping("/allLessonProgress/{uid}")
    public Map<String, Double> getProgressForAllLessons(@PathVariable String uid) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        List<LessonEntity> lessons = lessonRepo.findAll();
        return userProgressService.getProgressForAllLessons(user, lessons);
    }

    @PostMapping("/trackTopicView")
    public void trackTopicView(@RequestParam String uid, @RequestParam int topicId){
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        TopicEntity topic = topicRepo.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        userProgressService.trackTopicView(user, topic);
    }

    @GetMapping("/getAllUserProgress")
    public List<UserProgressEntity> getAllUserProgress(){
        return userProgressService.getAllUserProgress();
    }
}
