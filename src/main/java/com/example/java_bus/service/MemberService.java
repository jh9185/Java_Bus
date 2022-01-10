package com.example.java_bus.service;

import com.example.java_bus.vo.MemberVo;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    MemberVo save(MemberVo member);
    Optional<MemberVo> findById(Long memberNo);
    Optional<MemberVo> findByMemberId(String memberId);
    List<MemberVo> findAll();
    void deleteById(Long memberNo);
    void updateById(Long memberNo, MemberVo member);

    boolean loginCheck(String id, String pw);
}
