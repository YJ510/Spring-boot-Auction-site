package com.yj510.auction.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(nullable = false, unique = true)
        String email;

        @Column(nullable = false)
        String password;

        @Column(nullable = false)
        String name;

        @Column(nullable = false)
        //String password2;
        LocalDate birth;

}
