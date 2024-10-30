package com.bbeek.server.domain.auth.presentation;

import com.bbeek.server.domain.auth.presentation.dto.request.JoinUserRequest;
import com.bbeek.server.domain.auth.service.implementation.ReIssuer;
import com.bbeek.server.domain.auth.service.implementation.UserJoiner;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserJoiner userJoiner;
    private final ReIssuer reIssuer;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "사용자 가입", description = "사용자를 가입시킵니다.")
    public void joinProcess(
            @Parameter(description = "가입할 사용자 정보", required = true)
            @RequestBody JoinUserRequest joinUserRequest
    ) {
        System.out.println(joinUserRequest);
        userJoiner.joinProcess(joinUserRequest);
    }

    @PostMapping("/reissue")
    @Operation(summary = "토큰 재발급", description = "JWT 토큰을 재발급합니다.")
    public ResponseEntity<?> reissue(
            @Parameter(description = "HTTP 요청") HttpServletRequest request,
            @Parameter(description = "HTTP 응답") HttpServletResponse response
    ) {
        return reIssuer.reissue(request, response);
    }

}