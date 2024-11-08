package com.festival.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Board extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;
    private Long board_id;
    private String nickname;
    private String message;
    private LocalDateTime created_time;
}
