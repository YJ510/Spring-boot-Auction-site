package com.yj510.auction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    void signUp(Member member){
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }


}
