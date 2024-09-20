package com.IT334G4.Mathminds.OtherClasses;

import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.EmbeddedGameContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.ImageContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.QuestionContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.StoryboardContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.TextContent;
import com.IT334G4.Mathminds.OtherClasses.TopicContentTypes.YoutubeVidContent;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
    @JsonSubTypes.Type(value = TextContent.class),
    @JsonSubTypes.Type(value = QuestionContent.class, name = "question"),
    @JsonSubTypes.Type(value = ImageContent.class, name = "image"),
    @JsonSubTypes.Type(value = YoutubeVidContent.class, name = "youtubeVid"),
    @JsonSubTypes.Type(value = StoryboardContent.class, name = "storyboard"),
    @JsonSubTypes.Type(value = EmbeddedGameContent.class, name = "embeddedGame")
})
public abstract class TopicContent {
    protected String type;

    public String getType() {
        return type;
    }

    @JsonProperty
    public void setType(String type) {
        this.type = type;
    }
}

