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
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tblTopic")
public class TopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

    // CHECK COMMENTS BELOW Y I ADDED THIS ATTRIBUTE
    @Column(name = "lesson_id", insertable = false, updatable = false)
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

    public TopicEntity(int topicId, LessonEntity lesson, String topicTitle, Map<Integer, String> topicContent) {
        this.topicId = topicId;
        this.lesson = lesson;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
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

    // ADDED THE LESSON ID ATTRIBUTE SO THAT lessonId IT WILL ALSO BE INCLUDED WHEN FETCHING ALL TOPICS
    // TEMP FIX
    /*  JPA DOESN'T RETURN FOREIGN KEYS WHEN FETCHING
        https://stackoverflow.com/questions/72110341/find-all-data-using-foreign-key-from-referenced-table-in-spring-boot-jpa
        https://stackoverflow.com/questions/72133781/spring-jpa-not-returning-foreign-keys-in-response
        https://stackoverflow.com/questions/71991102/spring-data-jpa-findall-does-not-return-relations-data/71993222#71993222
    */
    
    // FUNCTION FOR INCLUDING THE LESSONID
    public int getLessonId(){
        return this.lessonId;
    }

    

}
