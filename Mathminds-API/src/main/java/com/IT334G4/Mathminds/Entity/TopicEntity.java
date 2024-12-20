package com.IT334G4.Mathminds.Entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.IT334G4.Mathminds.OtherClasses.TopicContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentConverter;
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

    @Column(name = "lesson_id", insertable = false, updatable = false)
    private int lessonId;

    private String topicTitle;

    @Column(length = 250)
    private String topicDescription;

    @ElementCollection
    @CollectionTable(name = "topic_content_mapping", joinColumns = @JoinColumn(name = "topic_id"))
    @MapKeyColumn(name = "order_index")
    @Column(name = "content", columnDefinition = "TEXT")
    @Convert(converter = TopicContentConverter.class, attributeName = "value")
    private Map<Integer, TopicContent> topicContent = new HashMap<>();

    
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserProgressEntity> userProgresses;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PracticeEntity> practices;

    private LocalDateTime topicDateAdded;

    private long topicViewCount;

    public TopicEntity() {
        super();
        this.topicDateAdded = LocalDateTime.now();
        this.topicViewCount = 0; 
    }

    public TopicEntity(int topicId, LessonEntity lesson, String topicTitle, Map<Integer, TopicContent> topicContent) {
        this.topicId = topicId;
        this.lesson = lesson;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.topicDateAdded = LocalDateTime.now();
        this.topicViewCount = 0;
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

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public Map<Integer, TopicContent> getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(Map<Integer, TopicContent> topicContent) {
        this.topicContent = topicContent;
    }

    public int getLessonId() {
        return this.lessonId;
    }

    public LocalDateTime getTopicDateAdded() {
        return topicDateAdded;
    }

    public void setTopicDateAdded(LocalDateTime topicDateAdded) {
        this.topicDateAdded = topicDateAdded;
    }

    public long getTopicViewCount() {
        return topicViewCount;
    }

    public void setTopicViewCount(long topicViewCount) {
        this.topicViewCount = topicViewCount;
    }

    
    
}
