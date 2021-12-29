package com.example.java_bus.service;

import com.example.java_bus.domain.Board;
import com.example.java_bus.domain.Member;
import com.example.java_bus.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberMapper memberMapper;

    //회원 수 조회
    public int memberCount(){
        return memberMapper.MemberCount();
    }

    @Transactional // 등록
    public void AddMember(Member member) throws Exception {
        IdCheck(member.getId());
        memberMapper.AddMember(member);
    }
    // 가입
    // 조회
    // 탈퇴
    // 로그인 여부
    public int IdCheck(String memberid) throws Exception {
        int result = memberMapper.IdCheck(memberid);
        return result;
    }

    public int LoginCheck(Member member) throws Exception {
        int result = memberMapper.LoginCheck(member);
        return result;
    }
}
