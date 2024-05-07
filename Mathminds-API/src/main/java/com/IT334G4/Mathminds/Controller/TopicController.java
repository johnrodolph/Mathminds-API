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

import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Service.TopicService;

@RestController
@RequestMapping("mathminds/topic")
@CrossOrigin
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("print")
    public String printHello(){
        return "Hello, working!";
    }

    @PostMapping("/insertTopic")
    public TopicEntity insertTopic(@RequestBody TopicEntity topic){
        return topicService.insertTopic(topic);
    }

    @GetMapping("/getAllTopics")
    public List<TopicEntity> getAllTopics(){
        return topicService.getAllTopics();
    }

    @PutMapping("/updateTopic")
    public TopicEntity updateTopic(@RequestParam int topicId, @RequestBody TopicEntity newTopicDetails){
        return topicService.updateTopic(topicId, newTopicDetails);
    }

    @DeleteMapping("/deleteTopic/{topicId}")
    public String deleteTopic(@PathVariable int topicId){
        return topicService.deleteTopic(topicId);
    }
}
