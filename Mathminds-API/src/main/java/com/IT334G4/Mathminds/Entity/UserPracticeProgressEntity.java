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
@Table(name = "tbl_user_practice")
public class UserPracticeProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "practice_id", nullable = false)
    private PracticeEntity practice;

    private boolean completed; // Track if the practice is completed
    private LocalDateTime timestamp; // When the practice was accessed
    private int viewCount; // How many times the user accessed this practice

    private LocalDateTime lastViewed;
    private LocalDateTime firstViewed;

    // Setters, Getters, and Constructors
    public UserPracticeProgressEntity() {
        super();
    }

    public UserPracticeProgressEntity(UserEntity user, PracticeEntity practice) {
        this.user = user;
        this.practice = practice;
        this.firstViewed = LocalDateTime.now();
        this.lastViewed = LocalDateTime.now();
        this.timestamp = LocalDateTime.now();
        //this.viewCount = 1;
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

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public PracticeEntity getPractice() {
        return practice;
    }

    public void setPractice(PracticeEntity practice) {
        this.practice = practice;
    }

    public LocalDateTime getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(LocalDateTime lastViewed) {
        this.lastViewed = lastViewed;
    }

    public LocalDateTime getFirstViewed() {
        return firstViewed;
    }

    public void setFirstViewed(LocalDateTime firstViewed) {
        this.firstViewed = firstViewed;
    }

}
