package com.example.commerce.member.entity;

import com.example.commerce.member.type.UserStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    private String userId;  // 사용자아이디

    private String userName;    // 이름
    private String userEmail;   // 이메일
    private String userPhone;   // 연락처
    private String userPassword;    // 비밀번호
    private String zipcode; // 우편번호
    private String address; // 주소
    private String addressDetail; // 상세주소

    private LocalDateTime createdAt; // 등록일

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus; // 유저 상태 (회원, 관리자)

    private boolean emailAuthYn; // 이메일 인증 여부
    private String emailAuthKey; // 이메일 인증키
    private LocalDateTime emailAuthAt; // 이메일 인증일





}
