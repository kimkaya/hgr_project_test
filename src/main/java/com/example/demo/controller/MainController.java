package com.example.demo.controller;

import com.example.demo.entity.dto.MemberDetailDTO;
import com.example.demo.entity.dto.MemberLoginDTO;
import com.example.demo.entity.dto.MemberSaveDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("save")
    public String saveForm() {
        return "member/save";
    }

    @PostMapping("save")
    public String save(@ModelAttribute MemberSaveDTO memberSaveDTO) {
        Long memberId = ms.save(memberSaveDTO);
        return "member/login";
    }

    @GetMapping("login")
    public String loginForm() {
        return "member/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute MemberLoginDTO memberLoginDTO, HttpSession session) {
        if (ms.login(memberLoginDTO)) {
            session.setAttribute(LOGIN_EMAIL,memberLoginDTO.getMemberEmail());
            return "member/mypage";
        } else {
            return "member/login";
        }
    }

    @GetMapping
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

    @GetMapping("{memberId}")
    public String findById(@PathVariable Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/findById";
    }

    @GetMapping("delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        ms.deleteById(memberId);
        return "redirect:/member/";
    }

    @DeleteMapping("{memberId}")
    public ResponseEntity deleteById2(@PathVariable Long memberId) {
        ms.deleteById(memberId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("update")
    public String updateForm(Model model, HttpSession session) {
        String memberEmail = (String) session.getAttribute(LOGIN_EMAIL);
        MemberDetailDTO memberDetailDTO = ms.findByEmail(memberEmail);
        model.addAttribute("member", memberDetailDTO);
        return "member/update";
    }
    @PostMapping("update")
    public String update(@ModelAttribute MemberDetailDTO memberDetailDTO) {
        Long memberId = ms.update(memberDetailDTO);
        return "redirect:/member/"+memberDetailDTO.getMemberId();
    }

    @PutMapping("{memberId}")
    public ResponseEntity update2(@RequestBody MemberDetailDTO memberDetailDTO) {
        Long memberId = ms.update(memberDetailDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
