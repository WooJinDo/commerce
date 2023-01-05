package com.example.commerce.member.service.impl;

import com.example.commerce.component.MailComponents;
import com.example.commerce.member.entity.Member;
import com.example.commerce.member.model.MemberParam;
import com.example.commerce.member.repository.MemberRepository;
import com.example.commerce.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            log.info("이미 userId가 생성되어 있습니다");
            return false;
        }

//        String encPassword = BCrypt.hashpw(memberParam.getPassword(), BCrypt.gensalt());

        String AuthKey = UUID.randomUUID().toString().replace("-", "");
        Member member = Member.builder()
                .userId(memberParam.getUserId())
                .userName(memberParam.getUserName())
                .userEmail(memberParam.getUserEmail())
                .userPhone(memberParam.getUserPhone())
                .userPassword(memberParam.getUserPassword())
                .zipcode(memberParam.getZipcode())
                .address(memberParam.getAddress())
                .addressDetail(memberParam.getAddressDetail())
                .createdAt(LocalDateTime.now())
                .emailAuthYn(false)
                .emailAuthKey(AuthKey)
                .build();

        memberRepository.save(member);

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
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다"));


//        Member member = emailAuthKey.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        memberRepository.save(member);

        return true;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Member> optionalMember = memberRepository.findById(username);
//        if (!optionalMember.isPresent()) {
//            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
//        }
//
//        Member member = optionalMember.get();
//
//        if (Member.MEMBER_STATUS_REQ.equals(member.getUserStatus())) {
//            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인을 해주세요.");
//        }
//
//        if (Member.MEMBER_STATUS_STOP.equals(member.getUserStatus())) {
//            throw new MemberStopUserException("정지된 회원 입니다.");
//        }
//
//        if (Member.MEMBER_STATUS_WITHDRAW.equals(member.getUserStatus())) {
//            throw new MemberStopUserException("탈퇴된 회원 입니다.");
//        }
//
//        if (!member.isEmailAuthYn()) {
//            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인을 해주세요.");
//        }
//
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        if (member.isAdminYn()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }
//
//        return new User(member.getUserId(), member.getPassword(),grantedAuthorities);
//    }


}
