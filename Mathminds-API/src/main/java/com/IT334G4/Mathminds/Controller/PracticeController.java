package com.IT334G4.Mathminds.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.PracticeEntity;
import com.IT334G4.Mathminds.Service.PracticeService;

@RestController
@RequestMapping("mathminds/practice")
@CrossOrigin
public class PracticeController {
    @Autowired
    PracticeService practiceService;

    @GetMapping("print")
    public String printHello(){
        return "Hello, Practice Controller working!!!";
    }

    @PostMapping("/insertPractice")
    public PracticeEntity insertPractice(@RequestBody PracticeEntity practice){
        return practiceService.insertPractice(practice);
    }

    @GetMapping("/getAllPractice")
    public List<PracticeEntity> getAllPractice(){
        return practiceService.getAllPractice();
    }

    @PutMapping("/updatePractice")
    public PracticeEntity updatePractice(@RequestParam int practiceId, @RequestBody PracticeEntity newPracticeDetails){
        return practiceService.updatePractice(practiceId, newPracticeDetails);
    }

    @DeleteMapping("/deletePractice/{practiceId}")
    public String deletePractice(@PathVariable int practiceId){
        return practiceService.deletePractice(practiceId);
    }
}
