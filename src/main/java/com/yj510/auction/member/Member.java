package com.yj510.auction.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@ToString
@Getter
@Setter
public class Member implements UserDetails {

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

        @Column(nullable = false)
        String role = "ROLE_USER"; // 기본 사용자 역할

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(() -> role);
        }

        @Override
        public String getUsername() {
                return email;
        }

        public void setPassword(String password) {
                this.password = new BCryptPasswordEncoder().encode(password);
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

}
