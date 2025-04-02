package com.yj510.auction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    String memberSignUp(){
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(String email, String name, String password, String passwordConfirm, LocalDate birth,
            Model model) {
        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setPassword(password);
        member.setBirth(birth);

        // 폼 데이터 확인
        System.out.println("Received email: " + member.getEmail());
        System.out.println("Received name: " + member.getName());
        System.out.println("Received password: " + member.getPassword());
        System.out.println("Received birth: " + member.getBirth());

        // 비밀번호 확인
        if (!member.getPassword().equals(passwordConfirm)) {
            model.addAttribute("error", "Passwords do not match.");
            return "member/signup"; // 다시 가입 페이지로 이동
        }

        // 회원 가입 처리
        memberService.signUp(member);
        return "member/login"; // 로그인 페이지로 이동
    }

    @GetMapping("/login")
    String memberLogin(){
        return "member/login";
    }

}
