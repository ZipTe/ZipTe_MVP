package com.eedo.project.zipte.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.order.Cart;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    // 장바구니 생성
    @OneToOne(mappedBy = "member")
    private Cart cart;

    @Embedded
    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;

    // 로직
    public static Member of(String email, String username, String phoneNumber, Address address) {
        List<Role> roles = new ArrayList<>();

        Member member = Member.builder()
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .address(address)
                .roles(roles)
                .createdAt(LocalDateTime.now())
                .build();

        member.addMemberRole(Role.OAUTH_FIRST_JOIN);
        member.addMemberRole(Role.USER);
        return member;
    }


    // 주소 추가 로직
    public void addAddress(Address address) {
        this.address = address;
    }

    // ROLE 관련 로직
    public void addMemberRole(Role role) {
        if (!this.roles.contains(role)) { // 중복 방지
            this.roles.add(role);
        }
    }

    public void removeMemberRole(Role role) {
        this.roles.remove(role); // Role 객체를 직접 삭제
    }

    // 이메일 추가 로직
    public void changeEmail(String email) {
        this.email = email;
    }

    // 유저 이름 추가 로직
    public void changeUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }


}