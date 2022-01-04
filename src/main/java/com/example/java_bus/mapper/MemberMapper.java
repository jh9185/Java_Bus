package com.example.java_bus.mapper;

import com.example.java_bus.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    Integer MemberCount();

    Integer AddMember(Member member);

    Integer  IdCheck(String memberId);

    Integer  LoginCheck(Member member);
}
