package com.bbeek.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // auth
    EMPTY_AUTHORIZATION_HEADER(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    SECRET_KEY_MISMATCH(HttpStatus.UNAUTHORIZED, "기존의 시크릿 키와 일치하지 않습니다."),
    AUTH_REQUIRED(HttpStatus.UNAUTHORIZED, "인증 정보가 필요합니다."),

    // user
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    INSUFFICIENT_POINTS(HttpStatus.FORBIDDEN, "포인트가 부족합니다."),
    USER_EXISTED(HttpStatus.CONFLICT, "유저가 이미 존재합니다."),

    // chat
    CHAT_NOT_ENGLISH(HttpStatus.BAD_REQUEST, "영어는 입력할 수 없습니다."),
    CHAT_NOT_BAD_WORD(HttpStatus.BAD_REQUEST, "부적절한 단어는 입력할 수 없습니다."),

    // item
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지를 조회할 수 없습니다."),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."),

    // inventory
    INVENTORY_NOT_FOUND(HttpStatus.NOT_FOUND, "인벤토리를 찾을 수 없습니다."),
    INVENTORY_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 가지고 있지 않습니다."),
    INVENTORY_ITEM_FOUND(HttpStatus.FOUND, "아이템을 이미 가지고 있습니다."),
    ITEM_ALREADY_EQUIPPED(HttpStatus.CONFLICT, "아이템이 이미 장착되었습니다."),

    // chapter
    CHAPTER_NOT_FOUND(HttpStatus.NOT_FOUND, "챕터를 찾을 수 없습니다."),

    // quiz
    QUIZ_NOT_FOUND(HttpStatus.NOT_FOUND, "문제를 찾을 수 없습니다."),
    STEP_NOT_FOUND(HttpStatus.NOT_FOUND, "단게를 찾을 수 없습니다."),

    // checklist
    CHECKLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "체크리스트를 찾을 수 없습니다."),

    //state
    STATE_NOT_FOUND(HttpStatus.NOT_FOUND, "상태를 찾을 수 없습니다."),

    //ai
    MOVE_NOT_FIT(HttpStatus.BAD_REQUEST, "move가 올바르지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
