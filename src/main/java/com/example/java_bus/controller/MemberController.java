package com.example.java_bus.controller;

import com.example.java_bus.service.MemberService;
import com.example.java_bus.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberService memberService;

    // 모든 회원 조회
    @GetMapping(value = "/memberTest", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<MemberVo>> getAllMembers() {
        List<MemberVo> member = memberService.findAll();
        return new ResponseEntity<List<MemberVo>>(member, HttpStatus.OK);
    }
    // 회원번호로 한명의 회원 조회
    @GetMapping(value = "/memberTest/{memberNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberVo> getMember(@PathVariable("memberNo") Long memberId) {
        Optional<MemberVo> member = memberService.findById(memberId);
        return new ResponseEntity<MemberVo>(member.get(), HttpStatus.OK);
    }

    // 회원 아이디로 한명의 회원 조회
    @PostMapping(value = "/memberTest/{memberId}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberVo> getMemberId(@PathVariable("memberId") String memberId) {
        //리스트 0 이면 없음
        //리스트 존재시 검사
        Optional<MemberVo> member = memberService.findByMemberId(memberId);
        return new ResponseEntity<MemberVo>(member.get(), HttpStatus.OK);
    }

    // 회원번호로 회원 삭제
    @DeleteMapping(value = "/memberTest/{memberNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteMember(@PathVariable("memberNo") Long memberNo) {
        memberService.deleteById(memberNo);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    // 회원번호로 회원 수정(mbrNo로 회원을 찾아 Board 객체의 id, name로 수정함)
    @PutMapping(value = "/memberTest/{memberNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<MemberVo> updateMember(@PathVariable("memberNo") Long memberNo, MemberVo member) {
        memberService.updateById(memberNo, member);
        return new ResponseEntity<MemberVo>(member, HttpStatus.OK);
    }
    // 회원 입력
    @PostMapping(value = "/memberTest")
    public ResponseEntity<MemberVo> save(MemberVo member) {
        return new ResponseEntity<MemberVo>(memberService.save(member), HttpStatus.OK);
    }
    // 회원 입력
    @RequestMapping(value="/memberTest/saveMember", method = RequestMethod.GET)
    public ResponseEntity<MemberVo> save(HttpServletRequest req, MemberVo member){
        return new ResponseEntity<MemberVo>(memberService.save(member), HttpStatus.OK);
    }

    // 회원 ID 중복 체크
    @RequestMapping("/memberTest/IdCheck")
    public String ID_Check(@RequestBody String paramData) throws Exception {
        //클라이언트가 보낸 ID값
        Optional<MemberVo> member = memberService.findByMemberId(paramData);

        if(!member.isEmpty()) {//결과 값이 있으면 아이디 존재
            return "-1";
        } else {		//없으면 아이디 존재 X
            System.out.println("사용 가능한 아이디입니다.");
            return "0";
        }
    }

    @RequestMapping(value="/loginProcess")
    public String loginProcess(HttpSession session, Model model,
                                     @RequestParam(value="id") String id,
                                     @RequestParam(value="password") String pw) {

        if(memberService.loginCheck(id, pw)){
            session.setAttribute("loginCheck", true);
            session.setAttribute("memberId", id);

            return "<script>alert('로그인 성공');location.href='/'</script>";
        }
        else{
            return "<script>alert('로그인 실패! 아이디 또는 비밀번호를 확인해주세요.');location.href='/Login'</script>";
        }
    }

    @GetMapping(value="/logoutProcess")
    public ModelAndView logoutProcess(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView("/"));

        HttpSession session = request.getSession();
        session.invalidate();

        return mav;
    }

}
