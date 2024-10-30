package com.bbeek.server.global.jwt.exception;

import com.bbeek.server.global.exception.security.BbeekSecurityException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BbeekSecurityException {

    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "잘못된 토큰입니다.");
    }
}
