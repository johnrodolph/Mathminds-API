package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;
import java.util.Map;

public class QuestionContent extends TopicContent{
    private String question;
    private String correctAnswer;
    private Map<Integer, String> incorrectAnswers;

    public QuestionContent() {
        this.type = "question";
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
