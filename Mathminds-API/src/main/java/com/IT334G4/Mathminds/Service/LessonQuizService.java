package com.IT334G4.Mathminds.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonQuizEntity;
import com.IT334G4.Mathminds.Repository.LessonQuizRepository;

@Service
public class LessonQuizService {
    @Autowired
    LessonQuizRepository lessonQuizRepo;

    public LessonQuizEntity insertLessonQuiz(LessonQuizEntity lessonQuiz){
        return lessonQuizRepo.save(lessonQuiz);
    }

    public List<LessonQuizEntity> getAllLessonsQuiz(){
        return lessonQuizRepo.findAll();
    }

    @SuppressWarnings("finally")
    public LessonQuizEntity updateLessonQuiz(int lessonQuizId, LessonQuizEntity newLessonQuizDetails){
        LessonQuizEntity lessonQuiz = new LessonQuizEntity();
        try{
            lessonQuiz = lessonQuizRepo.findById(lessonQuizId).get();
            lessonQuiz.setLessonQuizQA(newLessonQuizDetails.getLessonQuizQA());
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Lesson" + lessonQuizId + "does not exist");
        }finally{
            return lessonQuizRepo.save(lessonQuiz);
        }
    }

    public String deleteLessonQuiz(int lessonQuizId){
        String msg = "";
        if(lessonQuizRepo.findById(lessonQuizId) != null){
            lessonQuizRepo.deleteById(lessonQuizId);
            msg = "LessonQuiz " + lessonQuizId + " is succesfully deleted.";
        }else {
            msg = "LessonQuiz " + lessonQuizId + " does not exist.";
        }
        return msg;
    }
}
