package com.festival.Control;

import com.festival.DTO.MemberDTO;
import com.festival.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지로 이동
    @GetMapping("/signIn")
    public String loginPage(Model model) {
        return "member/login";  // 로그인 페이지로 이동
    }

    // 로그인 처리
    @PostMapping("/signIn")
    public String login(@RequestParam("id") String id,  // userId 대신 id 사용
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        if (memberService.validateLogin(id, password)) {  // 로그인 검증
            session.setAttribute("userId", id);  // 세션에 id 저장
            return "redirect:/";  // 로그인 후 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("loginFailMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "member/login";  // 로그인 실패 시 로그인 페이지로 돌아감
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화
        return "redirect:/";  // 로그아웃 후 메인 페이지로 리다이렉트
    }

    // 아이디/비밀번호 찾기 페이지로 이동
    @GetMapping("/findInfo")
    public String findInfoPage(Model model) {
        return "member/findInfo";  // 아이디/비밀번호 찾기 페이지
    }

    // 아이디/비밀번호 찾기 처리
    @PostMapping("/findInfo")
    public String findInfo(@RequestParam("action") String action,  // "findId" 또는 "findPassword" 액션 확인
                           @RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "userId", required = false) String userId,
                           @RequestParam(value = "nameForPassword", required = false) String nameForPassword,
                           @RequestParam(value = "emailForPassword", required = false) String emailForPassword,
                           Model model) {

        if ("findId".equals(action)) {  // 아이디 찾기 처리
            String foundId = memberService.findIdByNameAndEmail(name, email);
            if (foundId != null) {
                model.addAttribute("foundId", foundId);  // 찾은 아이디 표시
            } else {
                model.addAttribute("findFailMsg", "이름과 이메일에 해당하는 아이디가 없습니다.");
            }
        } else if ("findPassword".equals(action)) {  // 비밀번호 찾기 처리
            boolean isSent = memberService.sendPasswordResetLink(userId, nameForPassword, emailForPassword);
            if (isSent) {
                model.addAttribute("resetLinkMsg", "비밀번호 재설정 링크가 이메일로 발송되었습니다.");
            } else {
                model.addAttribute("resetFailMsg", "정보가 일치하지 않습니다.");
            }
        }

        return "member/findInfo";  // 아이디/비밀번호 찾기 결과 페이지
    }

    // 회원가입 페이지로 이동
    @GetMapping("/signUp")
    public String signUpPage(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/join";  // 회원가입 페이지
    }

    // 회원가입 처리
    @PostMapping("/signUp")
    public String signUp(@ModelAttribute MemberDTO memberDTO, Model model) {
        boolean isRegistered = memberService.registerMember(memberDTO);
        if (isRegistered) {
            model.addAttribute("signUpSuccessMsg", "회원가입이 완료되었습니다.");
            return "redirect:/member/signIn";  // 회원가입 후 로그인 페이지로 리다이렉트
        } else {
            model.addAttribute("signUpFailMsg", "회원가입에 실패하였습니다.");
            return "member/join";  // 회원가입 실패 시 회원가입 페이지로 돌아감
        }
    }

    // 로그인 상태에 따라 버튼에 필요한 정보 제공 (로그인 상태 체크)
    @ModelAttribute
    public void addLoginInfo(HttpSession session, Model model) {
        // 로그인 상태 확인
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            // 로그인 상태일 경우 세션에 userId 존재
            model.addAttribute("isLoggedIn", true);
        } else {
            // 로그인 안 된 상태
            model.addAttribute("isLoggedIn", false);
        }
    }
}


//    // 아이디 중복 확인 처리
//    @GetMapping("/checkDuplicateId")
//    @ResponseBody  // JSON 응답을 보냄
//    public Map<String, Boolean> checkDuplicateId(@RequestParam("id") String id) {
//        boolean isDuplicate = memberService.isUserIdExists(id);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("isDuplicate", isDuplicate);
//        return response;  // 중복 여부 반환
//    }
//
//    // 닉네임 중복 확인 처리
//    @GetMapping("/checkDuplicateNickname")
//    @ResponseBody  // JSON 응답을 보냄
//    public Map<String, Boolean> checkDuplicateNickname(@RequestParam("nickname") String nickname) {
//        boolean isDuplicate = memberService.isNicknameExists(nickname);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("isDuplicate", isDuplicate);
//        return response;  // 중복 여부 반환
//    }
