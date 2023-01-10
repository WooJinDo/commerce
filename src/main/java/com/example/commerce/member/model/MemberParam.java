package com.example.commerce.member.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberParam {

    private String userId;  // 사용자아이디
    private String userName;    // 이름
    private String userEmail;   // 이메일
    private String userPhone;   // 연락처
    private String userPassword;    // 비밀번호
    private String zipcode; // 우편번호
    private String address; // 주소
    private String addressDetail; // 상세주소
    private boolean adminYn; // 관리자여부
    private LocalDateTime createdAt; // 등록일
    private LocalDateTime updatedAt; // 수정일
    private String userStatus;  // 이용가능한상태, 정지상태
    private boolean emailAuthYn; // 이메일 인증 여부
    private String emailAuthKey; // 이메일 인증키
    private LocalDateTime emailAuthAt; // 이메일 인증일
    private String resetPasswordKey; // 비밀번호 초기화 key
    private LocalDateTime resetPasswordLimitAt; // 비밀번호 초기화 limit 기간
}


