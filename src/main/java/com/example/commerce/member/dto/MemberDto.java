package com.example.commerce.member.dto;

import com.example.commerce.member.entity.Member;
import com.example.commerce.member.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {

    private String userId;  // 사용자아이디
    private String userName;    // 이름
    private String userEmail;   // 이메일
    private String userPhone;   // 연락처
    private String userPassword;    // 비밀번호
    private String zipcode; // 우편번호
    private String address; // 주소
    private String addressDetail; // 상세주소

    private LocalDateTime createdAt; // 등록일

    private UserStatus userStatus; // 유저 상태 (회원, 관리자)

    private boolean emailAuthYn; // 이메일 인증 여부
    private LocalDateTime emailAuthAt; // 이메일 인증일
    private String emailAuthKey; // 이메일 인증키


    // 추가 컬럼
    private long totalCount;
    private long seq;

    public static void fromEntity(Member member) {
        MemberDto.builder()
                .userId(member.getUserId())
                .userName(member.getUserName())
                .userEmail(member.getUserEmail())
                .userPhone(member.getUserPhone())
                .userPassword(member.getUserPassword())
                .zipcode(member.getZipcode())
                .address(member.getAddress())
                .addressDetail(member.getAddressDetail())
                .createdAt(member.getCreatedAt())
                .userStatus(member.getUserStatus())
                .emailAuthYn(member.isEmailAuthYn())
                .emailAuthKey(member.getEmailAuthKey())
                .build();
    }
}
