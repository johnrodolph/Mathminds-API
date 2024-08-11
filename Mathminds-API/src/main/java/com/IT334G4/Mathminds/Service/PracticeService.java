package com.IT334G4.Mathminds.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
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


}
