package com.bbeek.server.global.jwt.filter;

import com.bbeek.server.domain.auth.domain.repository.RefreshRepository;
import com.bbeek.server.global.jwt.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();
        if (!requestUri.matches("^\\/logout$") || !request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshNormal = jwtUtil.getTokenFromCookies(request, "refresh_normal");

        if (refreshNormal != null) {
            processLogout(refreshNormal, "refresh_normal", response);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void processLogout(String refreshToken, String cookieName, HttpServletResponse response) {
        try {
            jwtUtil.isExpired(refreshToken);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Boolean isExist = refreshRepository.existsByRefreshToken(refreshToken);
        if (!isExist) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        refreshRepository.deleteByRefreshToken(refreshToken);

        log.warn("로그아웃 필터 동작");
        ResponseCookie invalidRefreshCookie = jwtUtil.invalidCookie(cookieName);
        response.addHeader(HttpHeaders.SET_COOKIE, invalidRefreshCookie.toString());

        String accessCookieName = cookieName.replace("refresh", "access");
        ResponseCookie invalidAccessCookie = jwtUtil.invalidCookie(accessCookieName);
        response.addHeader(HttpHeaders.SET_COOKIE, invalidAccessCookie.toString());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}