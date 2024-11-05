package com.festival.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String name;
    private String id;
    private String password;
    private String email;
    private String emailDomain;
    private String local;
    private String nickname;
    private Integer age;
    private String phone_number;
    private boolean agreeTerms; // boolean 타입으로 수정
}