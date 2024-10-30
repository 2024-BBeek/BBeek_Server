package com.bbeek.server.global.jwt.exception;

import com.bbeek.server.global.exception.security.BbeekSecurityException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends BbeekSecurityException {

    public RefreshTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, "REFRESH_TOKEN_NOT_FOUND", "리프레시 토큰이 존재하지 않습니다.");
    }
}
