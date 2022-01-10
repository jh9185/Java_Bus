package com.example.java_bus.service.impl;

import com.example.java_bus.repository.MemberRepository;
import com.example.java_bus.service.MemberService;
import com.example.java_bus.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<MemberVo> findAll() {
        List<MemberVo> members = new ArrayList<>();
        memberRepository.findAll().forEach(e -> members.add(e));
        return members;
    }
    public Optional<MemberVo> findById(Long MemberVo) {
        Optional<MemberVo> member = memberRepository.findById(MemberVo);
        return member;
    }

    @Override
    public Optional<MemberVo> findByMemberId(String memberId) {
        Optional<MemberVo> member = memberRepository.findByMemberId(memberId);
        return member;
    }

    public void deleteById(Long memberNo) {
        memberRepository.deleteById(memberNo);
    }
    public MemberVo save(MemberVo member) {
        memberRepository.save(member);
        return member;
    }
    public void updateById(Long memberNo, MemberVo member) {
        Optional<MemberVo> e = memberRepository.findById(memberNo);
        if (e.isPresent()) {
            e.get().setMemberId(member.getMemberId());
            e.get().setPassword(member.getPassword());
            memberRepository.save(member);
        }
    }

    public boolean loginCheck(String id, String pw) {
        Optional<MemberVo> member = memberRepository.findByMemberId(id);

        if(member.isEmpty()) {
            return false;
        }
        else {
            if(member.get().getPassword().equals(pw))
            {
                return true;
            }
            else {
                return false;
            }
        }
    }
}
