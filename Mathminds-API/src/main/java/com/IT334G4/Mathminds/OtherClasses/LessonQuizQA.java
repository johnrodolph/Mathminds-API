package com.IT334G4.Mathminds.OtherClasses;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lessonquiz_qa")
public class LessonQuizQA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int lessonquizqaId;

    private String question;
    private String correctAnswer;

    @ElementCollection
    // ct == collection table
    @CollectionTable(name = "lesson_quiz_incorrect_answers_ct", joinColumns = @JoinColumn(name = "lessonquizqa_id"))
    @Column(name = "incorrect_answer")
    private List<String> incorrectAnswers = new ArrayList<>();

    public LessonQuizQA(){
        super();
    }

    public LessonQuizQA(int lessonquizqaId, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.lessonquizqaId = lessonquizqaId;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getLessonquizqaId() {
        return lessonquizqaId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    
}
