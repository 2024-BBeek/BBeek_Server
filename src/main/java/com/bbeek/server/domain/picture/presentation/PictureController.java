package com.bbeek.server.domain.picture.presentation;

import com.bbeek.server.domain.ai.service.AiService;
import com.bbeek.server.domain.picture.presentation.dto.PictureResponse;
import com.bbeek.server.domain.picture.presentation.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.bbeek.server.global.jwt.util.AuthenticationUtil.getId;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    private final AiService aiService;

    @PostMapping
    public PictureResponse checkPicture(
            @RequestBody Request request
            ) {
        String menu = aiService.handlePictureRequest(request.img());
        List<String> menu1 = aiService.handleMenuRequest(menu);
        return aiService.handleNotEatRequest(menu, menu1.toString(), getId());
//        return aiService.handleNotEatRequest(menu, menu1.toString(), 1L);
    }

}
