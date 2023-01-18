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
}


