package com.festival.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDTO {
    private Long message_id;
    private Long board_id;
    private String nickname;
    private String message;
    private LocalDateTime created_time;
}
