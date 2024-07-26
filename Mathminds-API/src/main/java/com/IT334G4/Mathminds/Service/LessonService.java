package com.IT334G4.Mathminds.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonEntity;
import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.LessonRepository;
import com.IT334G4.Mathminds.Repository.UserRepository;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserProgressService userProgressService;

    public LessonEntity insertLesson(LessonEntity lesson){
        return lessonRepo.save(lesson);
    }

    public List<LessonEntity> getAllLessons(){
        return lessonRepo.findAll();
    }

    @SuppressWarnings("finally")
    public LessonEntity updateLesson(int lessonId, LessonEntity newLessonDetails){
        LessonEntity lesson = new LessonEntity();
        try{
            lesson = lessonRepo.findById(lessonId).get();
            lesson.setLessonTitle(newLessonDetails.getLessonTitle());
            lesson.setLessonDescription(newLessonDetails.getLessonDescription());

        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Lesson" + lessonId + "does not exist");
        }finally{
            return lessonRepo.save(lesson);
        }
    }

    public String deleteLesson(int lessonId){
        String message = "";
        String lessonTitle = "";
        LessonEntity lesson = new LessonEntity();
        try{
            lesson = lessonRepo.findById(lessonId).get();
            lessonTitle = lesson.getLessonTitle();

            lessonRepo.deleteById(lessonId);

            message = "Lesson: " + lessonTitle + " has successfully been deleted";
        }catch(NoSuchElementException ex){
            message = "Lesson " + lessonId + " does not exist.";
        }
        
        return message;
    }

    public LessonEntity getLessonById(int lessonId){
        LessonEntity lesson = new LessonEntity();
        lesson = lessonRepo.findById(lessonId).orElseThrow(() ->new NoSuchElementException("Lesson " + lessonId + " does not exist."));
        return lesson;
    }

    public Map<String, Object> getLessonWithProgress(int lessonId, String uid) {
        LessonEntity lesson = getLessonById(lessonId);
        UserEntity user = userRepo.findById(uid)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Map<String, Object> response = new HashMap<>();
        response.put("lesson", lesson);
        
        Map<Integer, Boolean> topicProgress = new HashMap<>();
        for (TopicEntity topic : lesson.getLessonTopics()) {
            boolean completed = userProgressService.isTopicCompleted(user, topic);
            topicProgress.put(topic.getTopicId(), completed);
        }
        response.put("progress", topicProgress);
        
        return response;
    }
}
