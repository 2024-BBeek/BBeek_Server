package com.bbeek.server.global.exception.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class BbeekSecurityException extends AuthenticationException {
    private final HttpStatus status;
    private final String errorCode;

    public BbeekSecurityException(HttpStatus status, String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
