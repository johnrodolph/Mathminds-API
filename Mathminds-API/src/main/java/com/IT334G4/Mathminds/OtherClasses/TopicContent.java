package com.IT334G4.Mathminds.OtherClasses;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicContent {
    private String type;
    private String content;
    private String question;
    private String correctAnswer;
    private Map<Integer, String> incorrectAnswers;

    // Constructors
    public TopicContent() {}

    public TopicContent(String content) {
        this.content = content;
    }

    public TopicContent(String question, String correctAnswer, Map<Integer, String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Map<Integer, String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(Map<Integer, String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
}
