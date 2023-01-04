package com.example.commerce.member.service;

import com.example.commerce.member.model.MemberParam;

public interface MemberService {

    /**
     * 사용자 회원가입
     */
    boolean register(MemberParam memberParam);

    /**
     * 이메일 인증키에 해당하는 계정을 활성화
     */
    boolean emailAuth(String AuthKey);


}
