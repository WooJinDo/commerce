package com.example.commerce.member.repository;

import com.example.commerce.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmailAuthKey(String emailAuthKey);
    Member findByUserEmailAndUserId(String userEmail, String userId);
//    Optional<Member> findByUserIdAndUserName(String userId, String userName);
    Member findByResetPasswordKey(String resetPasswordKey);

}
