package com.IT334G4.Mathminds.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.PracticeRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;
import com.IT334G4.Mathminds.Service.UserAnalyticsService;

@RestController
@RequestMapping("mathminds/student/analytics")
@CrossOrigin
public class UserAnalyticsController {
    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PracticeRepository practiceRepo;

    @GetMapping("/individualStudentDashboardData")
    public ResponseEntity<Map<String, Object>> getOverviewDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = userAnalyticsService.getIndividualUserDashboardData(uid);
        return ResponseEntity.ok(analyticsData);
    }

    @PostMapping("/trackPracticeView")
    public ResponseEntity<String> trackPracticeView(@RequestParam String uid, @RequestParam int practiceId) {
        try {
            UserEntity user = userRepo.findById(uid).orElseThrow(() -> new RuntimeException("User not found"));
            PracticeEntity practice = practiceRepo.findById(practiceId).orElseThrow(() -> new RuntimeException("Practice not found"));
            
            userAnalyticsService.trackPracticeView(user, practice);
            
            return ResponseEntity.ok("Practice view tracked successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
