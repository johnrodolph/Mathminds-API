package com.IT334G4.Mathminds.OtherClasses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter(autoApply = true)
public class TopicContentConverter implements AttributeConverter<TopicContent, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TopicContent topicContent) {
        try {
            return objectMapper.writeValueAsString(topicContent);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting TopicContent to JSON", e);
        }
    }

    @Override
    public TopicContent convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, TopicContent.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON to TopicContent", e);
        }
    }
}
