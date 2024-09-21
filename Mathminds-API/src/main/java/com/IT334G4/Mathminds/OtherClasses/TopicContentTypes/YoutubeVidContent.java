package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;

public class YoutubeVidContent extends TopicContent {
    private String youtubeLink;
    private String youtubeVidDescription;

    public YoutubeVidContent() {
        this.type = "youtubeVid";
    }

    // Getters and setters
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
