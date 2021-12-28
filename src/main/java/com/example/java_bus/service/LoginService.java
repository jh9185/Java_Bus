package com.example.java_bus.service;

import com.example.java_bus.domain.Board;
import com.example.java_bus.mapper.BoardMapper;
import com.example.java_bus.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {
    private final LoginMapper loginMapper;

    // 가입
    // 조회
    // 탈퇴
    // 로그인 여부
}
