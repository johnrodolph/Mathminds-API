package com.IT334G4.Mathminds.OtherClasses;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicContent {
    private String type;
    private String content;
    private String question;
    private String correctAnswer;
    private Map<Integer, String> incorrectAnswers;
    private String imageUrl;
    private String imageDescription;

    private List<String> storyboardAnimations;
    private String storyboardBgImage;

    private String youtubeLink;
    private String youtubeVidDescription;

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

    public TopicContent(String type, String param1, String param2) {
        this.type = type;
        switch (type) {
            case "image":
                this.imageUrl = param1;
                this.imageDescription = param2;
                break;
            case "youtube":
                this.youtubeLink = param1;
                this.youtubeVidDescription = param2;
                break;
        }
    }

    public TopicContent(List<String> storyboardAnimations, String storyboardBgImage){
        this.storyboardAnimations = storyboardAnimations;
        this.storyboardBgImage = storyboardBgImage;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public List<String> getStoryboardAnimations() {
        return storyboardAnimations;
    }

    public void setStoryboardAnimations(List<String> storyboardAnimations) {
        this.storyboardAnimations = storyboardAnimations;
    }

    public String getStoryboardBgImage() {
        return storyboardBgImage;
    }

    public void setStoryboardBgImage(String storyboardBgImage) {
        this.storyboardBgImage = storyboardBgImage;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getYoutubeVidDescription() {
        return youtubeVidDescription;
    }

    public void setYoutubeVidDescription(String youtubeVidDescription) {
        this.youtubeVidDescription = youtubeVidDescription;
    }
    
}
