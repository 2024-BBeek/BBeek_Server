package com.bbeek.server.domain.picture.presentation;

import com.bbeek.server.domain.ai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import static com.bbeek.server.global.jwt.util.AuthenticationUtil.getId;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    private final AiService aiService;

    @PostMapping
    public void checkPicture(
            @RequestPart("img") MultipartFile img
    ) throws IOException {
        String menu = aiService.handlePictureRequest(img);
        List<String> menu1 = aiService.handleMenuRequest(menu);
//        aiService.handleNotEatRequest(menu, menu1.toString(), getId());
        System.out.println(menu1);
    }

}
