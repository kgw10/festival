package com.festival.Control;

import com.festival.DTO.LoginDTO;
import com.festival.DTO.MemberDTO;
import com.festival.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signIn")
    public String loginPage(Model model) {
        model.addAttribute("LoginDTO", new LoginDTO());
        return "member/login";
    }

    @PostMapping("/signIn")
    public String login(@ModelAttribute LoginDTO loginDTO, Model model) {
        if (memberService.validateLogin(loginDTO.getId(), loginDTO.getPassword())) {
            return "redirect:/";
        } else {
            model.addAttribute("loginFailMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "member/login";
        }
    }

    @GetMapping("/findInfo")
    public String findInfoPage(Model model) {
        return "member/findInfo";
    }

    @PostMapping("/findId")
    public String findId(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         Model model) {
        String foundId = memberService.findIdByNameAndEmail(name, email);
        if (foundId != null) {
            model.addAttribute("foundId", foundId);
        } else {
            model.addAttribute("findFailMsg", "이름과 이메일에 해당하는 아이디가 없습니다.");
        }
        return "member/findInfo";
    }

    @PostMapping("/findPassword")
    public String findPassword(@RequestParam("userId") String userId,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               Model model) {
        boolean isSent = memberService.sendPasswordResetLink(userId, name, email);
        if (isSent) {
            model.addAttribute("resetLinkMsg", "비밀번호 재설정 링크가 이메일로 발송되었습니다.");
        } else {
            model.addAttribute("resetFailMsg", "정보가 일치하지 않습니다.");
        }
        return "member/findInfo";
    }

    @GetMapping("/signUp")
    public String signUpPage(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/join";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute MemberDTO memberDTO, Model model) {
        boolean isRegistered = memberService.registerMember(memberDTO);
        if (isRegistered) {
            model.addAttribute("signUpSuccessMsg", "회원가입이 완료되었습니다.");
            return "redirect:/member/signIn";
        } else {
            model.addAttribute("signUpFailMsg", "회원가입에 실패하였습니다.");
            return "member/join";
        }
    }

    @GetMapping("/checkDuplicateId")
    public ResponseEntity<Boolean> checkDuplicateId(@RequestParam String userId) {
        boolean isDuplicate = memberService.isUserIdExists(userId);
        return ResponseEntity.ok(isDuplicate);
    }

    @GetMapping("/checkDuplicateNickname")
    public ResponseEntity<Boolean> checkDuplicateNickname(@RequestParam String nickname) {
        boolean isDuplicate = memberService.isNicknameExists(nickname);
        return ResponseEntity.ok(isDuplicate);
    }
}