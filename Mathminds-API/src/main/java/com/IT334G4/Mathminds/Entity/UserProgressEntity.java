package com.IT334G4.Mathminds.Entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_userProgress")
public class UserProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topic;

    private boolean completed;

    private LocalDateTime timestamp;

    private int userViewCount; // Track individual user's views of this topic
    private LocalDateTime firstViewed; // When user first accessed the topic
    private LocalDateTime lastViewed; // Last time user viewed the topic

    public UserProgressEntity() {
        super();
    }

    public UserProgressEntity(UserEntity user, TopicEntity topic, boolean completed) {
        this.user = user;
        this.topic = topic;
        this.completed = completed;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserViewCount() {
        return userViewCount;
    }

    public void setUserViewCount(int userViewCount) {
        this.userViewCount = userViewCount;
    }

    public LocalDateTime getFirstViewed() {
        return firstViewed;
    }

    public void setFirstViewed(LocalDateTime firstViewed) {
        this.firstViewed = firstViewed;
    }

    public LocalDateTime getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(LocalDateTime lastViewed) {
        this.lastViewed = lastViewed;
    }

    
}
