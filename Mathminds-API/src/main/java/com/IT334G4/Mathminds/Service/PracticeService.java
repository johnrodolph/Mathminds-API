package com.IT334G4.Mathminds.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
import com.IT334G4.Mathminds.OtherClasses.PracticeQA;
import com.IT334G4.Mathminds.Repository.PracticeRepository;

@Service
public class PracticeService {
    @Autowired
    PracticeRepository practiceRepo;

    // CREATE
    public PracticeEntity insertPractice(PracticeEntity practice){
        return practiceRepo.save(practice);
    }

    // READ
    public List<PracticeEntity> getAllPractice(){
        return practiceRepo.findAll();
    }

    // UPDATE
    @SuppressWarnings("finally")
    public PracticeEntity updatePractice(int practiceId, PracticeEntity newPracticeDetails){
        PracticeEntity practice = new PracticeEntity();
        try{
            practice = practiceRepo.findById(practiceId).get();
            practice.setPractice_qa(newPracticeDetails.getPractice_qa());

        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Practice " + practiceId + " does not exist.");
        }finally{
            return practiceRepo.save(practice);
        }
    }

    // DELETE
    public String deletePractice(int practiceId){
        String msg = "";
        if(practiceRepo.findById(practiceId) != null){
            practiceRepo.deleteById(practiceId);
            msg = "Topic " + practiceId + " is succesfully deleted.";
        }else {
            msg = "Topic " + practiceId + " does not exist.";
        }
        return msg;
    }

    public List<PracticeEntity> getPracticeByTopicId(int topicId) {
        return practiceRepo.findByTopicTopicId(topicId);
    }

    public List<PracticeQA> getRandomizedPracticeByTopicId(int topicId) {
        final int maxQuestions = 10; // Set the default maximum to 10
        List<PracticeEntity> practiceList = practiceRepo.findByTopicTopicId(topicId);
        List<PracticeQA> allQuestions = new ArrayList<>();

        // Extract all questions from the practice entities
        for (PracticeEntity practice : practiceList) {
            for (Map.Entry<Integer, PracticeQA> entry : practice.getPractice_qa().entrySet()) {
                allQuestions.add(entry.getValue());
            }
        }

        // Randomize the questions
        Collections.shuffle(allQuestions);

        // Return a sublist of up to maxQuestions
        return allQuestions.subList(0, Math.min(maxQuestions, allQuestions.size()));
    }


}
