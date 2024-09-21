package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;

public class ImageContent extends TopicContent{
    private String imageUrl;
    private String imageDescription;

    public ImageContent() {
        this.type = "image";
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

    
}
