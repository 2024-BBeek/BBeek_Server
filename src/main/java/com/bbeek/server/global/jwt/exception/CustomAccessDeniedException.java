package com.bbeek.server.global.jwt.exception;

import com.bbeek.server.global.exception.security.BbeekSecurityException;
import org.springframework.http.HttpStatus;

public class CustomAccessDeniedException extends BbeekSecurityException {
    public CustomAccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "ACCESS_DENIED", "권한이 필요합니다.");
    }
}
