package com.bbeek.server.global.jwt.util;

import com.bbeek.server.domain.auth.domain.Refresh;
import com.bbeek.server.domain.auth.domain.repository.RefreshRepository;
import com.bbeek.server.global.jwt.exception.ExpiredTokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final RefreshRepository refreshRepository;
    @Value("${spring.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

    public JwtUtil(@Value("${spring.jwt.secret}") String secret, RefreshRepository refreshRepository) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshRepository = refreshRepository;
    }

    public Long getId(String token) {
        return Long.valueOf(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("sub", String.class));
    }

    public void isExpired(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    public String createAccessToken(Long id) {
        return createJwt(id, accessTokenExpiration);
    }

    public String createRefreshToken(Long id) {
        return createJwt(id, refreshTokenExpiration);
    }

    public ResponseCookie createAccessCookie(String value){
        return createCookie("access_normal", value, (int) accessTokenExpiration);
    }

    public ResponseCookie createRefreshCookie(String value){
        return createCookie("access_normal", value, (int) refreshTokenExpiration);
    }

    public ResponseCookie invalidCookie(String key){
        return createCookie(key, null, 0);
    }

    public void addRefreshToken(Long userId, String refreshToken) {

        Date date = new Date(System.currentTimeMillis() + refreshTokenExpiration);

        Refresh refresh = Refresh.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .expiration(date.toString())
                .build();

        refreshRepository.save(refresh);
    }

    public String getTokenFromCookies(HttpServletRequest request, String tokenName) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> tokenName.equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private String createJwt(Long id, long expiredMs) {
        return Jwts.builder()
                .claim("sub", String.valueOf(id))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    private ResponseCookie createCookie(String key, String value, int maxAge) {

        ResponseCookie cookie = ResponseCookie.from(key, value)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
        return cookie;

    }
}
