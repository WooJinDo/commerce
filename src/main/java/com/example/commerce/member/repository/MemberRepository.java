package com.example.commerce.member.repository;

import com.example.commerce.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmailAuthKey(String emailAuthKey);
//    Optional<Member> findByUserIdAndUserName(String userId, String userName);
//    Optional<Member> findByResetPasswordKey(String resetPasswordKey);

}