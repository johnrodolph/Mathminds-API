package com.IT334G4.Mathminds.OtherClasses;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class PracticeQADeserializer extends JsonDeserializer<Map<Integer, PracticeQA>> {

    @Override
    public Map<Integer, PracticeQA> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Map<Integer, PracticeQA> practiceQAList = new HashMap<>();
        JsonNode node = p.getCodec().readTree(p);

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            Integer orderIndex = Integer.parseInt(entry.getKey());

            JsonNode qaNode = entry.getValue();
            String question = qaNode.get("question").asText();
            String correctAnswer = qaNode.get("correctAnswer").asText();
            List<String> incorrectAnswers = new ArrayList<>();

            for (JsonNode incorrectAnswerNode : qaNode.get("incorrectAnswers")) {
                incorrectAnswers.add(incorrectAnswerNode.asText());
            }

            PracticeQA qa = new PracticeQA();
            qa.setQuestion(question);
            qa.setCorrectAnswer(correctAnswer);
            qa.setIncorrectAnswers(incorrectAnswers);

            practiceQAList.put(orderIndex, qa);
        }
        return practiceQAList;
    }
}
