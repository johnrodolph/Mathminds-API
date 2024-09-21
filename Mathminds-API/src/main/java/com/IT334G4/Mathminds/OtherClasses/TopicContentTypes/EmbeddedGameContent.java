
package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;

public class EmbeddedGameContent extends TopicContent {
    private String embeddedGameLink;
    private String embeddedGameName;
    private String embeddedGameTags;

    public EmbeddedGameContent() {
        this.type = "embeddedGame";
    }

    // Getters and setters
    public String getEmbeddedGameLink() {
        return embeddedGameLink;
    }

    public void setEmbeddedGameLink(String embeddedGameLink) {
        this.embeddedGameLink = embeddedGameLink;
    }

    public String getEmbeddedGameName() {
        return embeddedGameName;
    }

    public void setEmbeddedGameName(String embeddedGameName) {
        this.embeddedGameName = embeddedGameName;
    }

    public String getEmbeddedGameTags() {
        return embeddedGameTags;
    }

    public void setEmbeddedGameTags(String embeddedGameTags) {
        this.embeddedGameTags = embeddedGameTags;
    }
}
