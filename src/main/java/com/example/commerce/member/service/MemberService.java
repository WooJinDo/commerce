package com.example.commerce.member.service;

import com.example.commerce.member.model.MemberParam;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    /**
     * 사용자 회원가입
     */
    boolean register(MemberParam memberParam);

    /**
     * 이메일 인증키에 해당하는 계정을 활성화
     */
    boolean emailAuth(String AuthKey);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
    boolean sendResetPassword(MemberParam memberParam);

    /**
     * 입력받은 초기화키 대하여 password 초기화
     */
    boolean resetPassword(String resetPasswordKey, String userPassword);

    /**
     * 입력받은 초기화키 값이 유효한지 확인
     */
    boolean checkResetPassword(String resetPasswordKey);
}
