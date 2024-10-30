package com.bbeek.server.global.jwt.exception;

import com.bbeek.server.global.exception.security.BbeekSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends BbeekSecurityException {

    public ExpiredRefreshTokenException(HttpStatus status, String errorCode, String message) {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_REFRESH_TOKEN", "재로그인 해야 합니다.");
    }
}
