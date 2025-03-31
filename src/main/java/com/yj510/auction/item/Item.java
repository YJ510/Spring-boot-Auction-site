package com.yj510.auction.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@ToString
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본키 (자동 증가)

    @Column(name = "PLNM_NO", nullable = false)
    private Long publicNoticeNo;  // 공고번호

    @Column(name = "PBCT_NO", nullable = false)
    private Long auctionNo;  // 공매번호

    @Column(name = "PBCT_CDTN_NO", nullable = false)
    private Long auctionConditionNo;  // 공매조건번호

    @Column(name = "CLTR_NO", nullable = false)
    private Long itemNo;  // 물건번호

    @Column(name = "CLTR_HSTR_NO", nullable = false)
    private Long itemHistoryNo;  // 물건이력번호

    @Column(name = "SCRN_GRP_CD", length = 4, nullable = false)
    private String screenGroupCode;  // 화면그룹코드

    @Column(name = "CTGR_FULL_NM", length = 100, nullable = false)
    private String categoryFullName;  // 용도명

    @Column(name = "BID_MNMT_NO", length = 50, nullable = false)
    private String bidNo;  // 입찰번호

    @Column(name = "CLTR_NM", length = 1000, nullable = false)
    private String itemName;  // 물건명

    @Column(name = "CLTR_MNMT_NO", length = 50, nullable = false)
    private String itemManagementNo;  // 물건관리번호

    @Column(name = "LDNM_ADRS", length = 1000, nullable = false)
    private String landAddress;  // 물건소재지(지번)

    @Column(name = "NMRD_ADRS", length = 1000, nullable = false)
    private String roadAddress;  // 물건소재지(도로명)

    @Column(name = "LDNM_PNU", length = 20)
    private String landPnu;  // 지번PNU

    @Column(name = "DPSL_MTD_CD", length = 10, nullable = false)
    private String disposalMethodCode;  // 처분방식코드

    @Column(name = "DPSL_MTD_NM", length = 100, nullable = false)
    private String disposalMethodName;  // 처분방식코드명

    @Column(name = "BID_MTD_NM", length = 100, nullable = false)
    private String bidMethodName;  // 입찰방식명

    @Column(name = "MIN_BID_PRC", nullable = false)
    private Long minBidPrice;  // 최저입찰가

    @Column(name = "APSL_ASES_AVG_AMT", nullable = false)
    private Long appraisalAmount;  // 감정가

    @Column(name = "FEE_RATE", length = 10, nullable = false)
    private String feeRate;  // 최저입찰가율

    @Column(name = "PBCT_BEGN_DTM", length = 14, nullable = false)
    private String bidStartDateTime;  // 입찰시작일시 (YYYYMMDDHH24MISS)

    @Column(name = "PBCT_CLS_DTM", length = 14, nullable = false)
    private String bidEndDateTime;  // 입찰마감일시 (YYYYMMDDHH24MISS)

    @Column(name = "PBCT_CLTR_STAT_NM", length = 100, nullable = false)
    private String itemStatus;  // 물건상태

    @Column(name = "USCBD_CNT", nullable = false)
    private int failedBidCount;  // 유찰횟수

    @Column(name = "IQRY_CNT", nullable = false)
    private int viewCount;  // 조회수

    @Column(name = "GOODS_NM", length = 4000)
    private String itemDetail;  // 물건상세정보

    @Column(name = "MANF", length = 100)
    private String manufacturer;  // 제조사

    @Column(name = "MDL", length = 100)
    private String model;  // 모델

    @Column(name = "NRGT", length = 100)
    private String yearMonth;  // 연월식

    @Column(name = "GRBX", length = 100)
    private String transmission;  // 변속기

    @Column(name = "ENDPC", length = 100)
    private String displacement;  // 배기량

    @Column(name = "VHCL_MLGE", length = 100)
    private String mileage;  // 주행거리

    @Column(name = "FUEL", length = 100)
    private String fuelType;  // 연료

    @Column(name = "SCRT_NM", length = 100)
    private String corporationName;  // 법인명

    @Column(name = "TPBZ", length = 100)
    private String businessType;  // 업종

    @Column(name = "ITM_NM", length = 100)
    private String itemTypeName;  // 종목명

    @Column(name = "MMB_RGT_NM", length = 100)
    private String membershipName;  // 회원권명

    @Column(name = "CLTR_IMG_FILE", length = 2000)
    private String itemImageUrl;  // 물건 이미지 URL
}
