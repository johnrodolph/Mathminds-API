package com.IT334G4.Mathminds.Entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_user_badge")
public class UserBadgeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userBadgeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lesson;

    private LocalDateTime awardedDate;

    public UserBadgeEntity(){
        super();
    }

    public UserBadgeEntity(UserEntity user, LessonEntity lesson, LocalDateTime awardedDate) {
        this.user = user;
        this.lesson = lesson;
        this.awardedDate = awardedDate;
    }

    public int getUserBadgeIdId() {
        return userBadgeId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public LocalDateTime getAwardedDate() {
        return awardedDate;
    }

    public void setAwardedDate(LocalDateTime awardedDate) {
        this.awardedDate = awardedDate;
    }

    
    
}
