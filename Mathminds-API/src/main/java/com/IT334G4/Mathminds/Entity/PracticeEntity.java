package com.IT334G4.Mathminds.Entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.IT334G4.Mathminds.OtherClasses.PracticeQA;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "tblPractice")
public class PracticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int practiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIgnoreProperties({"topicContent", "topicDescription", "topicContent", "lessonId"})
    private TopicEntity topic;

    @ElementCollection
    @CollectionTable(name = "practice_qa_mapping", joinColumns = @JoinColumn(name = "practice_id"))
    @MapKeyColumn(name = "order_index")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<Integer, PracticeQA> practice_qa = new HashMap<>();
    public PracticeEntity() {
        super();
    }

    public PracticeEntity(int practiceId, TopicEntity topic, Map<Integer, PracticeQA> practice_qa) {
        this.practiceId = practiceId;
        this.topic = topic;
        this.practice_qa = practice_qa;
    }

    public int getPracticeId() {
        return practiceId;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public Map<Integer, PracticeQA> getPractice_qa() {
        return practice_qa;
    }

    public void setPractice_qa(Map<Integer, PracticeQA> practice_qa) {
        this.practice_qa.clear();
        if(practice_qa != null){
            this.practice_qa.putAll(practice_qa);
        }
    }
    

}