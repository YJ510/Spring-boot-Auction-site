package com.yj510.auction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void memberSignup(MemberCreateDTO memberCreateDTO){
        Member member = new Member();
        member.setEmail(memberCreateDTO.getEmail());
        member.setName(memberCreateDTO.getName());
        member.setPassword(passwordEncoder.encode(memberCreateDTO.getPassword()));
        member.setBirth(memberCreateDTO.getBirth());
        member.setRole("ADMIN");
        memberRepository.save(member);
    }
}
