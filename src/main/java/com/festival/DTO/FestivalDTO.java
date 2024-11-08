package com.festival.DTO;

import com.festival.Entity.Festival;
import lombok.Data;

@Data
public class FestivalDTO {
    private Long festival_id;
    private String title;  // 축제명
    private String addr1;  // 주소
    private String addr2;  // 행사장소
    private Integer eventstartdate;  // 시작일
    private Integer eventenddate;  // 종료일
    private String firstimage;  // 이미지
    private String mapx;  // 지도x좌표
    private String mapy;  // 지도y좌표
    private String areacode;  // 광역시도코드
    private String sigungucode;  // 시군구코드
    private String tel;  // 전화번호
//    private String festival_summary;  // 설명
//    private String charge;  // 이용료
//    private String sponsor;  // 스폰서-주최자
//    private String homepage;  // 공식홈페이지
//    private LocalDateTime regTime;
//    private LocalDateTime updateTime;
//    private String createdBy;
//    private String modifiedBy;
    public FestivalDTO(Festival festival){
        this.festival_id=festival.getFestival_id();
        this.title=festival.getTitle();
        this.addr1=festival.getAddr1();
        this.addr2=festival.getAddr2();
        this.eventstartdate=festival.getEventstartdate();
        this.eventenddate=festival.getEventenddate();
        this.firstimage=festival.getFirstimage();
        this.mapx=festival.getMapx();
        this.mapy=festival.getMapy();
        this.areacode=festival.getAreacode();
        this.sigungucode=festival.getSigungucode();
        this.tel=festival.getTel();
//        this.festival_summary=festival.getFestival_summary();
//        this.charge=festival.getCharge();
//        this.sponsor=festival.getSponsor();
//        this.homepage=festival.getHomepage();
//        this.regTime=festival.getRegTime();
//        this.updateTime=festival.getUpdateTime();
//        this.createdBy=festival.getCreatedBy();
//        this.modifiedBy=festival.getModifiedBy();
    }
}
