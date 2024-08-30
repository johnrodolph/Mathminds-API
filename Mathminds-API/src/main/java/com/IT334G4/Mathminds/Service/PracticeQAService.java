package com.IT334G4.Mathminds.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.PracticeQaMappingEntity;
import com.IT334G4.Mathminds.OtherClasses.PracticeQA;
import com.IT334G4.Mathminds.Repository.PracticeQAMappingRepository;

@Service
public class PracticeQAService {

    @Autowired
    private PracticeQAMappingRepository practiceQAMappingRepository;

    public List<PracticeQA> getPracticeQAsForPractice(int practiceId) {
        List<PracticeQA> practiceQAs = practiceQAMappingRepository.findPracticeQAsByPracticeId(practiceId);

        // Debugging: Print out all the practice QAs
        practiceQAs.forEach(practiceQA -> {
            System.out.println("PracticeQA ID: " + practiceQA.getPracticeqaId() +
                    ", Question: " + practiceQA.getQuestion());
        });

        // Shuffle the list to randomize the order
        Collections.shuffle(practiceQAs);

        // Return a sublist of up to 10 questions
        return practiceQAs.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

}
