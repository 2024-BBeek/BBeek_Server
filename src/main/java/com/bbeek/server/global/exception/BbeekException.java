package com.bbeek.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BbeekException extends RuntimeException {
    private final ErrorCode errorCode;
}

