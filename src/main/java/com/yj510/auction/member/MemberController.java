package com.yj510.auction.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member-signup")
    public String memberSignup() {
        return "member/signup";
    }

    @PostMapping("/member-signup")
    public String memberSignup(@ModelAttribute MemberCreateDTO memberCreateDTO) {
        memberService.memberSignup(memberCreateDTO);
        return "member/login";
    }
}
