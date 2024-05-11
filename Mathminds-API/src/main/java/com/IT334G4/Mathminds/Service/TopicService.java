package com.IT334G4.Mathminds.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.TopicEntity;
import com.IT334G4.Mathminds.Repository.TopicRepository;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepo;

    // C - Create or insert topic record in tbl_topic
    public TopicEntity insertTopic(TopicEntity topic){
        return topicRepo.save(topic);
    }

    // R -  Read or get all records in tbl_topic
    public List<TopicEntity> getAllTopics(){
        return topicRepo.findAll();
    }

    // U - Update topic info
    @SuppressWarnings("finally")
    public TopicEntity updateTopic(int topicId, TopicEntity newTopicDetails){
        TopicEntity topic = new TopicEntity();
        try{
            topic = topicRepo.findById(topicId).get();
            topic.setTopicTitle(newTopicDetails.getTopicTitle());
            topic.setTopicContent(newTopicDetails.getTopicContent());

        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("Topic " + topicId + " does not exist.");
        }finally{
            return topicRepo.save(topic);
        }
    }

    // D - Delete topic
    public String deleteTopic(int uid){
        String msg = "";
        if(topicRepo.findById(uid) != null){
            topicRepo.deleteById(uid);
            msg = "Topic " + uid + " is succesfully deleted.";
        }else {
            msg = "Topic " + uid + " does not exist.";
        }

        return msg;
    }

    // GET TOPIC BY topicId
    public TopicEntity getTopicByTopicId(int topicId){
        TopicEntity topic = new TopicEntity();
        topic = topicRepo.findById(topicId).orElseThrow(() ->new NoSuchElementException("Topic " + topicId + " does not exist."));
        return topic;
    } 
}
