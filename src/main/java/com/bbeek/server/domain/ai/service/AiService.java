package com.bbeek.server.domain.ai.service;

//import com.bbeek.server.domain.user.domain.User;
//import com.bbeek.server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AiService {

//    private final UserRepository userRepository;
    private final OpenAiChatModel openAiChatModel;
    @Value("${prompt.system}")
    private String promptSystem;
    @Value("${prompt.allergy}")
    private String allergyPrompt;
    @Value("${prompt.met}")
    private String metPrompt;
    @Value("${prompt.picture}")
    private String picturePrompt;
    @Value("${prompt.notEat}")
    private String notEatPrompt;

    public List<String> handleAllergyRequest(String allergy, String product) {
        PromptTemplate promptTemplate = new PromptTemplate(promptSystem + allergyPrompt);

        Prompt prompt = promptTemplate.create(Map.of("all", allergy, "product", product), OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_4_O).build());

        String content = openAiChatModel.call(prompt).getResult().getOutput().getContent().replace(" ", "").replace("없음", "");
        if (content.isEmpty()) {
            return List.of();
        } else {
            return List.of(content.split(","));
        }
    }

    public List<String> handleMenuRequest(String menu) {
        PromptTemplate promptTemplate = new PromptTemplate(promptSystem + metPrompt);

        Prompt prompt = promptTemplate.create(Map.of("menu", menu), OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_4_O_MINI).build());

        return List.of(openAiChatModel.call(prompt).getResult().getOutput().getContent().replace(" ", "").split(","));
    }

    public String handlePictureRequest(MultipartFile imageData) throws IOException {

        InputStreamResource imageResource = new InputStreamResource(imageData.getInputStream());

        UserMessage userMessage = new UserMessage(promptSystem + picturePrompt,
                List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)));

        return openAiChatModel.call(userMessage);
    }

    public List<String> handleNotEatRequest(String menu, String menu1, Long userId) {

//        User user = userRepository.findById(userId).orElseThrow();
        //TODO user 값 안에서 순서대로 못먹는 알레르기(food), 고기류(meal), 채식(style) 값 뒤에 넣기

        PromptTemplate promptTemplate = new PromptTemplate(promptSystem + notEatPrompt);

//        Prompt prompt = promptTemplate.create(Map.of("food", , "meal", , "style" , , "menu", menu, "menu1", menu1, ), OpenAiChatOptions.builder().withModel(OpenAiApi.ChatModel.GPT_4_O_MINI).build());

//        return List.of(openAiChatModel.call(prompt).getResult().getOutput().getContent().replace(" ", "").split(","));
        return List.of(openAiChatModel.call((Prompt) null).getResult().getOutput().getContent().replace(" ", "").split(","));

    }

}
