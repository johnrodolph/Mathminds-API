package com.IT334G4.Mathminds.Entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tblTopic")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;
    private int lessonId;

    private String topicTitle;

    @ElementCollection
    @CollectionTable(name = "topic_content_mapping", joinColumns = @JoinColumn(name = "topic_id"))
    @MapKeyColumn(name = "order_index")
    @Column(name = "content")
    private Map<Integer, String> topicContent = new HashMap<>();
    
    public TopicEntity() {
        super();
    }

    public TopicEntity(int topicId, int lessonId, String topicTitle, Map<Integer, String> topicContent) {
        this.topicId = topicId;
        this.lessonId = lessonId;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Map<Integer, String> getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(Map<Integer, String> topicContent) {
        this.topicContent = topicContent;
    }

    

}
