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
@Table(name = "practice_qa")
public class PracticeQA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int practiceqaId;

    @Column(length = 1000)
    private String question;
    private String correctAnswer;

    @ElementCollection
    // ct == collection table
    @CollectionTable(name = "practice_incorrect_answers_ct", joinColumns = @JoinColumn(name = "practiceqa_id"))
    @Column(name = "incorrect_answer")
    private List<String> incorrectAnswers = new ArrayList<>();

    public PracticeQA(){
        super();
    }

    public PracticeQA(int practiceqaId, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.practiceqaId = practiceqaId;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getPracticeqaId() {
        return practiceqaId;
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