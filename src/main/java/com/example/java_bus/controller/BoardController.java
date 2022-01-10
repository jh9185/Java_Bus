package com.example.java_bus.controller;

import com.example.java_bus.service.BoardService;
import com.example.java_bus.vo.BoardVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("boardTest")
public class BoardController { // 기본형
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BoardService boardService;
     // 모든 게시글 조회
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<BoardVo>> getAllBoards() {
        List<BoardVo> board = boardService.findAll();
        return new ResponseEntity<List<BoardVo>>(board, HttpStatus.OK);
    }
    // 게시글 번호로 조회
    @GetMapping(value = "/{boardNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BoardVo> getBoard(@PathVariable("boardNo") Long boardNo) {
        Optional<BoardVo> board = boardService.findById(boardNo);
        return new ResponseEntity<BoardVo>(board.get(), HttpStatus.OK);
    }
    // 게시글 번호로 삭제
    @DeleteMapping(value = "/{boardNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardNo") Long boardNo) {
        boardService.deleteById(boardNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    // 게시글번호로 수정(boardNo로 게시글 찾아 Board 객체의 id, name로 수정함)
    @PutMapping(value = "/{boardNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BoardVo> updateBoard(@PathVariable("boardNo") Long boardNo, BoardVo board) {
        boardService.updateById(boardNo, board);
        return new ResponseEntity<BoardVo>(board, HttpStatus.OK);
    }
    // 게시글 입력
    @PostMapping(value ="/upload", produces = { MediaType.APPLICATION_JSON_VALUE })
    public String save(BoardVo board) {
        board.setRegdate(LocalDateTime.now());
        boardService.save(board);
        return "redirect:../";
    }
}
