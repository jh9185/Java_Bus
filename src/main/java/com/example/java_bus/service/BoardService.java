package com.example.java_bus.service;

import com.example.java_bus.domain.Board;
import com.example.java_bus.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardMapper boardMapper;

    public int boardCount(){
        return boardMapper.boardCount();
    }

    // 게시글 갯수 가져오기
    public List<Board> boardList() {
        return boardMapper.getList();
    }

    // 내용 가져오기
    public Board getBoard(Long boardId){return boardMapper.getBoard(boardId);}

    @Transactional // 등록
    public void uploadBoard(Board board){
        boardMapper.uploadBoard(board);
    }

    @Transactional // 수정
    public void updateBoard(Board board){
        boardMapper.updateBoard(board);
    }

    @Transactional // 뷰 증가
    public void updateViewBoard(Board board){
        boardMapper.updateViewBoard(board);
    }

    @Transactional // 삭제
    public void deleteBoard(Long boardId){
        boardMapper.deleteBoard(boardId);
    }
}
