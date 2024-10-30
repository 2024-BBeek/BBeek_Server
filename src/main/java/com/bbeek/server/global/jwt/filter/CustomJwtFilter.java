package com.bbeek.server.global.jwt.filter;

import com.bbeek.server.global.jwt.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public class CustomJwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final List<String> excludedPaths;

    public CustomJwtFilter(JwtUtil jwtUtil, List<String> excludedPaths) {
        this.jwtUtil = jwtUtil;
        this.excludedPaths = excludedPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        log.warn("자체 JWT 필터 uri : " + requestUri);

        String accessToken = jwtUtil.getTokenFromCookies(request, "access_normal");

        if (accessToken == null) {
            log.warn("Custom Access token not found in cookies.");
            filterChain.doFilter(request, response);
            return;
        }

        jwtUtil.isExpired(accessToken);

        Long id = jwtUtil.getId(accessToken);

        Authentication authToken = new UsernamePasswordAuthenticationToken(id, null, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern ->
                        new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
