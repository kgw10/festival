package com.festival.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PartyDTO {
    private Long board_id;
    private String nickname;
    private String festival_name;
    private String board_title;
    private int participant;
    private LocalDateTime godate;
    private LocalDateTime regTime;
}
