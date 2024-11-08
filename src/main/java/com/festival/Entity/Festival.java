package com.festival.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="festival1")
public class Festival extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long festival_id;
    private String title;
    private String addr1;
    private String addr2;
    private Integer eventstartdate;
    private Integer eventenddate;
    private String firstimage;
    private String mapx;
    private String mapy;
    private String areacode;
    private String sigungucode;
    private String tel;
//    @Column(columnDefinition = "TEXT")
//    private String festival_summary;
//    private String charge;
//    private String sponsor;
//    private String homepage;
}
