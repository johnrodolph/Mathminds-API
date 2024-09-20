package com.IT334G4.Mathminds.OtherClasses.TopicContentTypes;

import com.IT334G4.Mathminds.OtherClasses.TopicContent;

public class TextContent extends TopicContent{
    private String content;

    public TextContent() {
        this.type = "content";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
