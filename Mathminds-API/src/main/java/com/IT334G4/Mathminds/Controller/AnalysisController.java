package com.IT334G4.Mathminds.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Service.AnalyticsService;

@RestController
@RequestMapping("mathminds/teacher/analytics")
@CrossOrigin
public class AnalysisController {
    @Autowired
    private AnalyticsService analyticsService;


    @GetMapping("print")
    public String printHello(){
        return "Hello, Analytics Controller working!!!";
    }

    // Unused
    // @GetMapping("/basicCountAnalytics")
    // public ResponseEntity<Map<String, Object>> getAnalytics(@RequestParam String uid) {
    //     Map<String, Object> analyticsData = analyticsService.getAllAnalytics(uid);
    //     return ResponseEntity.ok(analyticsData);
    // }

    @GetMapping("/overviewDashboardContent")
    public ResponseEntity<Map<String, Object>> getOverviewDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = analyticsService.getOverviewDashboardContent(uid);
        return ResponseEntity.ok(analyticsData);
    }

    @GetMapping("/lessonDashboardContent")
    public ResponseEntity<Map<String, Object>> getLessonDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = analyticsService.getLessonDashboardContent(uid);
        return ResponseEntity.ok(analyticsData);
    }

    @GetMapping("/topicDashboardContent")
    public ResponseEntity<Map<String, Object>> getTopicDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = analyticsService.getTopicDashboardContent(uid);
        return ResponseEntity.ok(analyticsData);
    }

    @GetMapping("/studentDashboardContent")
    public ResponseEntity<Map<String, Object>> getStudentDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = analyticsService.getStudentDashboardContent(uid);
        return ResponseEntity.ok(analyticsData);
    }

}
