package com.IT334G4.Mathminds.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.LessonRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;
import com.IT334G4.Mathminds.Service.UserBadgeService;

@RestController
@RequestMapping("mathminds/userBadge")
@CrossOrigin
public class UserBadgeController {
    @Autowired
    UserBadgeService userBadgeService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LessonRepository lessonRepo;

    @GetMapping("print")
    public String printHello(){
        return "Hello, UserBadge Controller working!!!";
    }

    @GetMapping("/hasUserEarnedBadge")
    public boolean hasUserEarnedBadge(@RequestParam String uid, @RequestParam int lessonId) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        LessonEntity lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new RuntimeException("Topic not found"));
        return userBadgeService.hasUserEarnedBadge(user, lesson);
    }
    
    @PutMapping("/awardBadge")
    public String awardBadge(@RequestParam String uid, @RequestParam int lessonId) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        LessonEntity lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new RuntimeException("Topic not found"));

        return userBadgeService.awardBadge(user, lesson);
    }

    @GetMapping("/getBadgesForUser")
    public Map<String, String> getBadgesForUser(@RequestParam String uid){
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
        return userBadgeService.getBadgesForUser(user);
    }
}
