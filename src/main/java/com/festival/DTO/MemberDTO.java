package com.festival.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String name;

    @Size(min = 8, max = 16, message = "영문 8글자 이상 16글자 이하로 작성하세요.")
    @NotBlank(message = "아이디는 필수입니다.")
    private String id;         // 사용자 ID (String 타입)

    @Size(min = 8, max = 16, message = "영문 숫자 포함하여 8글자 이상 16글자 이하로 작성하세요")
    private String password;

    @NotBlank(message = "이메일을 작성해 주세요")
    private String email;
    private String emailDomain;
    private String local;

    @Max(value = 20, message = "최대 20자 이내로 작성하세요.")
    private String nickname;

    @Size(min = 1, max=100, message = "나이는 0부터 100까지 입니다.")
    private Integer age;

    @Max(value = 11)
    private String phone_number;

    @NotBlank(message = "동의하지 않았습니다.")
    private boolean agreeTerms;
}