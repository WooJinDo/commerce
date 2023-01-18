package com.example.commerce.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
    USER_NOT_FOUND("회원이 존재하지 없습니다"),
    USER_ID_DUPLICATION("이미 회원가입된 ID 입니다");

    private final String description;
}
