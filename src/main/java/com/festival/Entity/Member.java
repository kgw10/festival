package com.festival.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id; // 멤버 엔티티의 PK는 Long

    private String name;  // 이름 필드 추가
    private String id;    // 사용자 ID (String 타입)

    private String password; // 로그인 비밀번호
    private String email;
    private String local;
    private String nickname;
    private Integer age;
    private String phone_number;
    private boolean agreeTerms;
}