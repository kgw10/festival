package com.festival.Service;

import com.festival.DTO.MemberDTO;
import com.festival.Entity.Member;
import com.festival.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 처리
    public boolean registerMember(MemberDTO memberDTO) {
        if (isUserIdExists(memberDTO.getId()) || isNicknameExists(memberDTO.getNickname())) {
            return false;  // 중복된 아이디나 닉네임이 있으면 가입 불가
        }

        Member member = convertToEntity(memberDTO);
        memberRepository.save(member);  // 회원 정보 저장
        return true;  // 성공적으로 저장
    }

    // DTO를 Entity로 변환
    private Member convertToEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPassword());
        member.setEmail(memberDTO.getEmail());
        member.setLocal(memberDTO.getLocal());
        member.setNickname(memberDTO.getNickname());
        member.setAge(memberDTO.getAge());
        member.setPhone_number(memberDTO.getPhone_number());
        member.setAgreeTerms(memberDTO.isAgreeTerms());
        return member;
    }

    // 아이디 중복 확인
    public boolean isUserIdExists(String id) {
        return memberRepository.existsById(id);
    }

    // 닉네임 중복 확인
    public boolean isNicknameExists(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    // 로그인 검증
    public boolean validateLogin(String id, String password) {
        Member member = memberRepository.findById(id);
        return member != null && member.getPassword().equals(password);
    }

    // 이름과 이메일로 아이디 찾기
    public String findIdByNameAndEmail(String name, String email) {
        Member member = memberRepository.findByNameAndEmail(name, email);
        return member != null ? member.getId() : null;
    }

    // 비밀번호 재설정 링크 발송
    public boolean sendPasswordResetLink(String id, String name, String email) {
        Member member = memberRepository.findById(id);
        if (member != null && member.getName().equals(name) && member.getEmail().equals(email)) {
            // 실제 비밀번호 재설정 링크 발송 로직을 구현해야 함
            // 예: 이메일 전송 로직 추가
            return true;
        }
        return false;  // 정보가 일치하지 않으면 실패
    }
}
