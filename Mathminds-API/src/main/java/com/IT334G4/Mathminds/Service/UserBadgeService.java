package com.IT334G4.Mathminds.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.UserBadgeEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.UserBadgeRepository;

@Service
public class UserBadgeService {
    @Autowired
    private UserBadgeRepository userBadgeRepo;

    public boolean hasUserEarnedBadge(UserEntity user, LessonEntity lesson) {
        return userBadgeRepo.existsByUserAndLesson(user, lesson);
    }

    public String awardBadge(UserEntity user, LessonEntity lesson) {
        if (!hasUserEarnedBadge(user, lesson)) {
            UserBadgeEntity userBadge = new UserBadgeEntity(user, lesson, LocalDateTime.now());
            userBadgeRepo.save(userBadge);
        }

        return "Congrats!"; // to edit l8er
    }
    

     public Map<String, String> getBadgesForUser(UserEntity user) {
        List<UserBadgeEntity> userBadges = userBadgeRepo.findByUser(user);
        
        return userBadges.stream()
            .collect(Collectors.toMap(
                userBadge -> userBadge.getLesson().getLessonTitle(),
                userBadge -> userBadge.getLesson().getLessonBadgeImageUrl()
            ));
    }
}
