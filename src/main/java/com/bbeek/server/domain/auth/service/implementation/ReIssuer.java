package com.bbeek.server.domain.auth.service.implementation;

import com.bbeek.server.domain.auth.domain.repository.RefreshRepository;
import com.bbeek.server.global.jwt.exception.InvalidTokenException;
import com.bbeek.server.global.jwt.exception.RefreshTokenNotFoundException;
import com.bbeek.server.global.jwt.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReIssuer {

    private final JwtUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        String refresh = jwtUtil.getTokenFromCookies(request, "refresh_normal");

        if (refresh == null) {
            log.warn("Refresh token not found in cookies");
            throw new RefreshTokenNotFoundException();
        }

        jwtUtil.isExpired(refresh);

        Boolean isExist = refreshRepository.existsByRefreshToken(refresh);

        if (!isExist) {
            log.warn("Refresh token not found in database: {}", refresh);
            throw new RefreshTokenNotFoundException();
        }

        Long id = jwtUtil.getId(refresh);

        String newAccess = jwtUtil.createAccessToken(id);
        String newRefresh = jwtUtil.createRefreshToken(id);

        refreshRepository.deleteByRefreshToken(refresh);
        jwtUtil.addRefreshToken(id, newRefresh);

        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createAccessCookie(newAccess).toString());
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshCookie(newRefresh).toString());

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
