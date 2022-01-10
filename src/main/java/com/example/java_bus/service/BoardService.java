package com.example.java_bus.service;

import com.example.java_bus.vo.BoardVo;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    BoardVo save(BoardVo board);
    Optional<BoardVo> findById(Long boardNo);
    List<BoardVo> findAll();
    void deleteById(Long boardNo);
    void updateById(Long boardNo, BoardVo board);
}
