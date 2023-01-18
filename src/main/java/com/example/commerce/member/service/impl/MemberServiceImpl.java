package com.example.commerce.member.service.impl;

import com.example.commerce.component.MailComponents;
import com.example.commerce.exception.MemberException;
import com.example.commerce.member.dto.MemberDto;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberParam;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.member.service.MemberService;
import com.example.commerce.exception.type.MemberErrorCode;
import com.example.commerce.member.type.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponents mailComponents;

//    private final MemberMapper memberMapper;

    /**
     * 회원 가입
     */
    @Override
    public boolean register(MemberParam memberParam) {
        Optional<Member> optionalMember = memberRepository.findById(memberParam.getUserId());

        // get() 메소드를 호출하기 전에 isPresent() 메소드를 사용하여
        // Optional 객체에 저장된 값이 null인지 아닌지를 먼저 확인한 후 호출
        if (optionalMember.isPresent()) {
            // 현재 userId에 해당하는 데이터 존재
            throw new MemberException(MemberErrorCode.USER_ID_DUPLICATION);
        }

        // 비밀번호 암호화
        String encPassword = BCrypt.hashpw(memberParam.getUserPassword(), BCrypt.gensalt());

        String AuthKey = UUID.randomUUID().toString().replace("-", "");

        MemberDto.fromEntity(
                memberRepository.save(
                    Member.builder()
                            .userId(memberParam.getUserId())
                            .userName(memberParam.getUserName())
                            .userEmail(memberParam.getUserEmail())
                            .userPhone(memberParam.getUserPhone())
                            .userPassword(encPassword)
                            .zipcode(memberParam.getZipcode())
                            .address(memberParam.getAddress())
                            .addressDetail(memberParam.getAddressDetail())
                            .createdAt(LocalDateTime.now())
                            .userStatus(UserStatus.USER)
                            .emailAuthYn(false)
                            .emailAuthKey(AuthKey)
                            .build()));

        String email = memberParam.getUserEmail();
        String subject = "commerce 사이트 가입을 축하드립니다.";
        String text = "<p>commerce 사이트 가입을 축하드립니다.<p>" +
                "<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>" +
                "<div><a href='http://localhost:8080/member/email_auth?id="
                + AuthKey + "'> 가입 완료 </a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }

    @Override
    public boolean emailAuth(String AuthKey) {

        // Optional.ofNullbale() - 값이 Null일수도, 아닐수도 있는 경우
        // orElseThrow() : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.
//        Optional<Member> emailAuthKey = Optional.ofNullable(memberRepository.findByEmailAuthKey(AuthKey))
//                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다"));
        Member member = Optional.ofNullable(memberRepository.findByEmailAuthKey(AuthKey))
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND));

//        Member member = emailAuthKey.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthAt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.USER_NOT_FOUND));

        if (!member.isEmailAuthYn()) {
            throw new MemberException("이메일 활성화가 안됐습니다");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (UserStatus.MANAGER.equals(member.getUserStatus())) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(member.getUserId(), member.getUserPassword(),grantedAuthorities);
    }


}
