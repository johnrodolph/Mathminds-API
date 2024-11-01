package com.IT334G4.Mathminds.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Entity.UserProgressEntity;
import com.IT334G4.Mathminds.Repository.TopicRepository;
import com.IT334G4.Mathminds.Repository.UserProgressRepository;

@Service
public class UserProgressService {
    @Autowired
    UserProgressRepository userProgressRepo;

    @Autowired
    TopicRepository topicRepo;

    // NOT USED, TESTING ONLY
    public List<UserProgressEntity> getProgressForUserAndLesson(UserEntity user, LessonEntity lesson) {
        List<TopicEntity> topics = lesson.getLessonTopics();
        return userProgressRepo.findByUserAndTopicIn(user, topics);
    }
    
    public double calculateLessonProgress(UserEntity user, LessonEntity lesson) {
        long totalTopics = lesson.getLessonTopics().size();
        if (totalTopics == 0) {
            return 0;
        }
        long completedTopics = userProgressRepo.countByUserAndTopicLessonAndCompleted(user, lesson, true);
        return (double) completedTopics / totalTopics * 100;
    }
    
    public void updateProgress(UserEntity user, TopicEntity topic, boolean completed) {
        UserProgressEntity progress = userProgressRepo.findByUserAndTopic(user, topic);
        if (progress == null) {
            progress = new UserProgressEntity(user, topic, completed);
        } else {
            progress.setCompleted(completed);
        }
        userProgressRepo.save(progress);
    }

    public Map<String, Double> getProgressForAllLessons(UserEntity user, List<LessonEntity> lessons) {
        return lessons.stream().collect(Collectors.toMap(
            LessonEntity::getLessonTitle,
            lesson -> calculateLessonProgress(user, lesson)
        ));
    }

    public boolean isTopicCompleted(UserEntity user, TopicEntity topic) {
        UserProgressEntity progress = userProgressRepo.findByUserAndTopic(user, topic);
        return progress != null && progress.isCompleted();
    }

    public void trackTopicView(UserEntity user, TopicEntity topic){
        UserProgressEntity progress = userProgressRepo.findByUserAndTopic(user, topic);
        LocalDateTime now = LocalDateTime.now();

        if(progress == null){
            // First time user is viewing this topic
            progress = new UserProgressEntity(user, topic, false);
            progress.setFirstViewed(now);
            progress.setUserViewCount(1);
        }else{
            // User has viewed this topic before
            progress.setUserViewCount(progress.getUserViewCount() + 1);
        }

        progress.setLastViewed(now);
        userProgressRepo.save(progress);

        // Update total view count on topic
        topic.setTopicViewCount(topic.getTopicViewCount() + 1);
        topicRepo.save(topic);
    }

    public List<UserProgressEntity> getAllUserProgress(){
        return userProgressRepo.findAll();
    }
    
}
