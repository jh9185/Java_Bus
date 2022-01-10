package com.example.java_bus.vo;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="member")
public class MemberVo {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String memberId;
    @Column
    private String password;

    @Builder
    public MemberVo(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
