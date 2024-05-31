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

import com.IT334G4.Mathminds.OtherClasses.LessonQuizQA;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lessonQuiz")
public class LessonQuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonQuizId;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lesson;

    @ElementCollection
    @CollectionTable(name = "lesson_quiz_mapping", joinColumns = @JoinColumn(name = "lesson_quiz_id"))
    @MapKeyColumn(name = "order_index")
    @OneToMany(cascade = CascadeType.ALL)
    private Map<Integer, LessonQuizQA> lessonQuizQA = new HashMap<>();

    public LessonQuizEntity(){
        super();
    }

    public LessonQuizEntity(int lessonQuizId, LessonEntity lesson, Map<Integer, LessonQuizQA> lessonQuizQA) {
        this.lessonQuizId = lessonQuizId;
        this.lesson = lesson;
        this.lessonQuizQA = lessonQuizQA;
    }

    public int getlessonQuizId() {
        return lessonQuizId;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public Map<Integer, LessonQuizQA> getLessonQuizQA() {
        return lessonQuizQA;
    }

    public void setLessonQuizQA(Map<Integer, LessonQuizQA> lessonQuizQA) {
        this.lessonQuizQA = lessonQuizQA;
    }

    public String getLessonTitle() {
        return lesson.getLessonTitle();
    }

    
}
