package com.yj510.auction.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MemberCreateDTO {
    private String email;
    private String name;
    private String password;
    private String passwordCofirm;
    private LocalDate birth;
    private String role;
}
