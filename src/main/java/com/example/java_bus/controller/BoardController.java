package com.example.java_bus.controller;

import com.example.java_bus.domain.Board;
import com.example.java_bus.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/hello")
    public String Hello(){
        return "/boards/hello";
    }

    @GetMapping({"/main", "/"})
    public String main(Model model){
        model.addAttribute("list", service.boardList());
        return "redirect:/main";
    }

    @GetMapping("/view")
    public String viewBoard(Model model, Long boardId){
        model.addAttribute("view", service.getBoard(boardId));
        return "/boards/view";
    }

    //등록
    @GetMapping("/upload")
    public String uploadBoardForm(){
        return "/boards/upload";
    }

    @PostMapping("/upload")
    public String uploadBoard(Board board){
        board.setRead(0);
        service.uploadBoard(board);
        return "redirect:/main";
    }

    //수정
    @PostMapping("/update")
    public String updateBoard(Board board){
        service.updateBoard(board);
        return "redirect:/main";
    }
    @PostMapping("/updateview")
    public String updateViewBoard(Board board){
        service.updateViewBoard(board);
        return "redirect:/main";
    }

    //삭제
    @PostMapping("/delete")
    public String deleteBoard(Long boardId){
        service.deleteBoard(boardId);
        return "redirect:/main";
    }
}
