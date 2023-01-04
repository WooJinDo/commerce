package com.example.commerce.member.dto;

import com.example.commerce.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private boolean adminYn; // 관리자여부
    private LocalDateTime createdAt; // 등록일
    private LocalDateTime updatedAt; // 수정일

    private boolean emailAuthYn; // 이메일 인증 여부
    private LocalDateTime emailAuthDt; // 이메일 인증일
    private String emailAuthKey; // 이메일 인증키

    private String resetPasswordKey;
    private LocalDateTime resetPasswordLimitDt;

    private String userStatus; // 이용가능한상태, 정지상태

    // 추가 컬럼
    private long totalCount;
    private long seq;

//    public static MemberDto of(Member member) {
//        return MemberDto.builder()
//                .userId(member.getUserId())
//                .userName(member.getUserName())
//                .phone(member.getPhone())
////                .password(member.getPassword())
//                .regDt(member.getRegDt())
//                .udtDt(member.getUdtDt())
//                .emailAuthYn(member.isEmailAuthYn())
//                .emailAuthDt(member.getEmailAuthDt())
//                .emailAuthKey(member.getEmailAuthKey())
//                .resetPasswordKey(member.getResetPasswordKey())
//                .resetPasswordLimitDt(member.getResetPasswordLimitDt())
//                .adminYn(member.isAdminYn())
//                .userStatus(member.getUserStatus())
//                .zipcode(member.getZipcode())
//                .addr(member.getAddr())
//                .addrDetail(member.getAddrDetail())
//                .build();
//    }
//
//    public String getRegDtText() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        return regDt != null ? regDt.format(formatter) : "";
//    }
//
//    public String getUptDtText() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        return udtDt != null ? udtDt.format(formatter) : "";
//    }
//
//    public String getLastLoginTimeText() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
//        return lastLoginTime != null ? lastLoginTime.format(formatter) : "";
//    }
}
