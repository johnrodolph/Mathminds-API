package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import java.util.List;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;

public class StoryboardContent extends TopicContent {
    private List<String> storyboardAnimations;
    private String storyboardBgImage;

    public StoryboardContent() {
        this.type = "storyboard";
    }

    // Getters and setters
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
}
