package com.bbeek.server.global.jwt.exception;

import com.bbeek.server.global.exception.security.BbeekSecurityException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends BbeekSecurityException {

    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 토큰입니다.");
    }
}
