package com.IT334G4.Mathminds.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Service.UserAnalyticsService;

@RestController
@RequestMapping("mathminds/student/analytics")
@CrossOrigin
public class UserAnalysisController {
    @Autowired
    private UserAnalyticsService userAnalyticsService;

    @GetMapping("/individualStudentDashboardData")
    public ResponseEntity<Map<String, Object>> getOverviewDashboardContent(@RequestParam String uid) {
        Map<String, Object> analyticsData = userAnalyticsService.getIndividualUserDashboardData(uid);
        return ResponseEntity.ok(analyticsData);
    }
}
