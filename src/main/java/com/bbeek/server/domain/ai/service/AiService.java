package com.bbeek.server.domain.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AiService {

    private final OpenAiChatModel openAiChatModel;
    @Value("${prompt.system}")
    private String promptSystem;
    @Value("${prompt.allergy}")
    private String allergyPrompt;

    public List<String> handleAllergyRequest(String allergy, String product) {
        PromptTemplate promptTemplate = new PromptTemplate(promptSystem + allergyPrompt);

        Prompt prompt = promptTemplate.create(Map.of("all", allergy, "product", product));

        List<String> result = List.of(openAiChatModel.call(prompt).getResult().getOutput().getContent().replace(" ", "").split(","));
        return result;
    }

//        public void handleAllergyRequest(MultipartFile imageData) throws IOException {
//
//            InputStreamResource imageResource = new InputStreamResource(imageData.getInputStream());
//
////        PromptTemplate promptTemplate = new PromptTemplate();
////
////        Prompt prompt = promptTemplate.create();
//
//        UserMessage userMessage = new UserMessage("prompt",
//                listOf(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)));
//
//        openAiChatModel.call(userMessage);
//    }

}