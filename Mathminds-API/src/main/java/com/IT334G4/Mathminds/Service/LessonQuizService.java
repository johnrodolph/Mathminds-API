package com.IT334G4.Mathminds.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.LessonQuizEntity;
import com.IT334G4.Mathminds.OtherClasses.LessonQuizQA;
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
            lessonQuiz.setIsAdministered(newLessonQuizDetails.getIsAdministered());
        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Lesson" + lessonQuizId + " does not exist");
        }finally{
            return lessonQuizRepo.save(lessonQuiz);
        }
    }

    public String deleteLessonQuiz(int lessonQuizId){
        String msg = "";
        if(lessonQuizRepo.findById(lessonQuizId) != null){
            lessonQuizRepo.deleteById(lessonQuizId);
            msg = "LessonQuiz " + lessonQuizId + " is successfully deleted.";
        }else {
            msg = "LessonQuiz " + lessonQuizId + " does not exist.";
        }
        return msg;
    }

    public LessonQuizEntity getLessonQuizById(int lessonQuizId){
        LessonQuizEntity lessonQuiz = new LessonQuizEntity();
        lessonQuiz = lessonQuizRepo.findById(lessonQuizId).orElseThrow(() ->new NoSuchElementException("LessonQuiz " + lessonQuizId + " does not exist."));
        return lessonQuiz;
    }

    public int checkQuizAdministered(int lessonQuizId) {
        LessonQuizEntity lessonQuiz = lessonQuizRepo.findById(lessonQuizId).orElseThrow(() -> new NoSuchElementException("LessonQuiz " + lessonQuizId + " does not exist."));
        return lessonQuiz.getIsAdministered() ? 1 : 0;
    }

    // New method to get randomized questions
    public List<LessonQuizQA> getRandomizedLessonQuizById(int lessonQuizId) {
        final int maxQuestions = 10; // Set the default maximum to 10
        LessonQuizEntity lessonQuiz = lessonQuizRepo.findById(lessonQuizId)
            .orElseThrow(() -> new NoSuchElementException("LessonQuiz " + lessonQuizId + " does not exist."));
        
        List<LessonQuizQA> allQuestions = new ArrayList<>();

        // Extract all questions from the lessonQuiz entity
        for (Map.Entry<Integer, LessonQuizQA> entry : lessonQuiz.getLessonQuizQA().entrySet()) {
            allQuestions.add(entry.getValue());
        }

        // Randomize the questions
        Collections.shuffle(allQuestions);

        // Return a sublist of up to maxQuestions
        return allQuestions.subList(0, Math.min(maxQuestions, allQuestions.size()));
    }
}
