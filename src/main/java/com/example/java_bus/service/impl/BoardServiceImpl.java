package com.example.java_bus.service.impl;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.vo.BoardVo;
import com.example.java_bus.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<BoardVo> findAll() {
        List<BoardVo> boards = new ArrayList<>();
        boardRepository.findAll().forEach(e -> boards.add(e));
        return boards;
    }
    public Optional<BoardVo> findById(Long boardNo) {
        Optional<BoardVo> board = boardRepository.findById(boardNo);
        return board;
    }
    public void deleteById(Long mbrNo) {
        boardRepository.deleteById(mbrNo);
    }
    public BoardVo save(BoardVo board) {
        boardRepository.save(board);
        return board;
    }
    public void updateById(Long boardNo, BoardVo board) {
        Optional<BoardVo> e = boardRepository.findById(boardNo);
        if (e.isPresent()) {
            e.get().setTitle(board.getTitle());
            e.get().setName(board.getName());
            e.get().setContent(board.getContent());
            e.get().setRegdate(board.getRegdate());
            boardRepository.save(e.get());
        }
    }
}