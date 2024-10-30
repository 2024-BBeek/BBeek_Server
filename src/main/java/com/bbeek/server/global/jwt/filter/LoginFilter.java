package com.bbeek.server.global.jwt.filter;

import com.bbeek.server.domain.auth.service.dto.CustomUserDetails;
import com.bbeek.server.global.jwt.dto.LoginRequest;
import com.bbeek.server.global.jwt.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        setUsernameParameter("userId");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            String userId = loginRequest.userId();
            String password = loginRequest.password();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userId, password);

            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication){

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long id = customUserDetails.getId();

        String accessToken = jwtUtil.createAccessToken(id);
        String refreshToken = jwtUtil.createRefreshToken(id);


        jwtUtil.addRefreshToken(id, refreshToken);
        log.warn("자체 로그인 필터 동작");
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie(accessToken).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie(refreshToken).toString());
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){

        response.setStatus(401);
    }

}
