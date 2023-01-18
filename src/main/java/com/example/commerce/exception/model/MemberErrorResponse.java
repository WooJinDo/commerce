package com.example.commerce.exception.model;

import com.example.commerce.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberErrorResponse {
    private MemberErrorCode errorCode;
    private String errorMessage;
}
