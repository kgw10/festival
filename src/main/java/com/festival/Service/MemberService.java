package com.festival.Service;

import com.festival.DTO.MemberDTO;
import com.festival.Entity.Member;
import com.festival.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean registerMember(MemberDTO memberDTO) {
        if (isUserIdExists(memberDTO.getId()) || isNicknameExists(memberDTO.getNickname())) {
            return false;
        }

        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setUserId(memberDTO.getId());
        member.setPassword(memberDTO.getPassword());
        member.setEmail(memberDTO.getEmail());
        member.setLocal(memberDTO.getLocal());
        member.setNickname(memberDTO.getNickname());
        member.setAge(memberDTO.getAge());
        member.setPhone_number(memberDTO.getPhone_number());

        memberRepository.save(member);
        return true;
    }

    public boolean isUserIdExists(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    public boolean isNicknameExists(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean validateLogin(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        return member != null && member.getPassword().equals(password);
    }

    public String findIdByNameAndEmail(String name, String email) {
        Member member = memberRepository.findByNameAndEmail(name, email);
        return member != null ? member.getUserId() : null;
    }

    public boolean sendPasswordResetLink(String userId, String name, String email) {
        return true;
    }
}