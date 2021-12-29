package com.example.java_bus.mapper;

import com.example.java_bus.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {
    int MemberCount();

    void AddMember(Member member);

    int  IdCheck(String memberid);

    int  LoginCheck(Member member);
}
