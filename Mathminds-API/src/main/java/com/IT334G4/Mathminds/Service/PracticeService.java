package com.IT334G4.Mathminds.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
    public PracticeEntity insertPractice(PracticeEntity practice) {
        return practiceRepo.save(practice);
    }

    // READ
    public List<PracticeEntity> getAllPractice() {
        return practiceRepo.findAll();
    }

    // UPDATE
    @SuppressWarnings("finally")
    public PracticeEntity updatePractice(int practiceId, PracticeEntity newPracticeDetails) {
        PracticeEntity practice = new PracticeEntity();
        try {
            practice = practiceRepo.findById(practiceId).get();
            practice.setPractice_qa(newPracticeDetails.getPractice_qa());

        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Practice " + practiceId + " does not exist.");
        } finally {
            return practiceRepo.save(practice);
        }
    }

    // DELETE
    @Transactional
    public String deletePractice(int topicId) {
        String msg = "";
        List<PracticeEntity> practices = practiceRepo.findByTopicTopicId(topicId);

        if (practices != null && !practices.isEmpty()) {
            practiceRepo.deleteByTopicTopicId(topicId);
            msg = "Practice with topicId " + topicId + " was successfully deleted.";
        } else {
            msg = "Practice with topicId " + topicId + " does not exist.";
        }
        return msg;
    }

    public List<PracticeEntity> getPracticeByTopicId(int topicId) {
        return practiceRepo.findByTopicTopicId(topicId);
    }

    public List<PracticeQA> getRandomizedPracticeByTopicId(int topicId, int maxQuestions) {
        List<PracticeEntity> practiceList = practiceRepo.findByTopicTopicId(topicId);
        List<PracticeQA> allQuestions = new ArrayList<>();

        for (PracticeEntity practice : practiceList) {
            for (Map.Entry<Integer, PracticeQA> entry : practice.getPractice_qa().entrySet()) {
                PracticeQA practiceQA = entry.getValue();

                List<String> filteredIncorrectAnswers = practiceQA.getIncorrectAnswers().stream()
                        .filter(answer -> answer != null && !answer.trim().isEmpty())
                        .collect(Collectors.toList());

                practiceQA.setIncorrectAnswers(filteredIncorrectAnswers);

                if (!filteredIncorrectAnswers.isEmpty()) {
                    allQuestions.add(practiceQA);
                }
            }
        }

        Collections.shuffle(allQuestions);

        return allQuestions.subList(0, Math.min(maxQuestions, allQuestions.size()));
    }

}
