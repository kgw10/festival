package com.festival.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Data
public class Party extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long board_id;
    private String nickname;  // 함께가요 작성자
    private String festival_name;  // 함께가요 축제 이름
    private String board_title;  // 함께가요 글 제목
    @Min(value = 2,message = "최소 2명 이상입니다.")
    @Max(value = 5,message = "최대 5명 이하입니다.")
    private int participant;  // 함께가요 참가인원
    private LocalDateTime godate;  // 참가일
}
