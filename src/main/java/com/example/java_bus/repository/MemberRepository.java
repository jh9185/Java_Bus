package com.example.java_bus.repository;

import com.example.java_bus.vo.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long>{
    public List<MemberVo> findById(String memberNo);
    public Optional<MemberVo> findByMemberId(String memberId);
    // like검색도 가능
//    public List<MemberVo> findByNameLike(String keyword);
}


