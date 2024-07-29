package com.IT334G4.Mathminds.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "tblLesson")
public class LessonEntity implements Serializable{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lessonId;
    private String lessonTitle;

    private String lessonDescription;

    @JsonManagedReference
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<TopicEntity> lessonTopics;

    @JsonManagedReference
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<LessonQuizEntity> lessonQuiz;

    private String lessonBadgeImageUrl;

    public LessonEntity(){
        super();
    }

    public LessonEntity(int lessonId, String lessonTitle, String lessonDescription, List<TopicEntity> lessonTopics, List<LessonQuizEntity> lessonQuiz, String lessonBadgeImageUrl) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonDescription = lessonDescription;
        this.lessonTopics = lessonTopics;
        this.lessonQuiz = lessonQuiz;
        this.lessonBadgeImageUrl = lessonBadgeImageUrl;
    }
    
    public int getLessonId() {
        return lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
    
    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public List<TopicEntity> getLessonTopics() {
        return lessonTopics;
    }

    public void setLessonTopics(List<TopicEntity> lessonTopics) {
        this.lessonTopics = lessonTopics;
    }

    public List<LessonQuizEntity> getLessonQuiz() {
        return lessonQuiz;
    }

    public void setLessonQuiz(List<LessonQuizEntity> lessonQuiz) {
        this.lessonQuiz = lessonQuiz;
    }

    public String getLessonBadgeImageUrl() {
        return lessonBadgeImageUrl;
    }

    public void setLessonBadgeImageUrl(String lessonBadgeImageUrl) {
        this.lessonBadgeImageUrl = lessonBadgeImageUrl;
    }

    

    

}
