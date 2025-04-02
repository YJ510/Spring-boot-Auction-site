package com.yj510.auction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isEmpty()){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.get().getEmail())
                .password(member.get().getPassword())
                .build();
    }

}
