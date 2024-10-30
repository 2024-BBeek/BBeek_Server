package com.bbeek.server.global.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class CalorieResponse {
    private Header header;
    private Body body;

    @Data
    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Data
    public static class Body {
        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("items")
        private List<Item> items;
    }

    @Data
    public static class Item {
        @JsonProperty("NUM")
        private String NUM;

        @JsonProperty("FOOD_CD")
        private String FOOD_CD;

        @JsonProperty("FOOD_NM_KR")
        private String FOOD_NM_KR;

        @JsonProperty("DB_GRP_CM")
        private String DB_GRP_CM;

        @JsonProperty("DB_GRP_NM")
        private String DB_GRP_NM;

        @JsonProperty("FOOD_OR_CD")
        private String FOOD_OR_CD;

        @JsonProperty("FOOD_OR_NM")
        private String FOOD_OR_NM;

        @JsonProperty("FOOD_CAT1_CD")
        private String FOOD_CAT1_CD;

        @JsonProperty("FOOD_CAT1_NM")
        private String FOOD_CAT1_NM;

        @JsonProperty("FOOD_REF_CD")
        private String FOOD_REF_CD;

        @JsonProperty("FOOD_REF_NM")
        private String FOOD_REF_NM;

        @JsonProperty("FOOD_CAT2_CD")
        private String FOOD_CAT2_CD;

        @JsonProperty("FOOD_CAT2_NM")
        private String FOOD_CAT2_NM;

        @JsonProperty("FOOD_CAT3_CD")
        private String FOOD_CAT3_CD;

        @JsonProperty("FOOD_CAT3_NM")
        private String FOOD_CAT3_NM;

        @JsonProperty("FOOD_CAT4_CD")
        private String FOOD_CAT4_CD;

        @JsonProperty("FOOD_CAT4_NM")
        private String FOOD_CAT4_NM;

        @JsonProperty("SERVING_SIZE")
        private String SERVING_SIZE;

        @JsonProperty("AMT_NUM1")
        private String AMT_NUM1;

        @JsonProperty("AMT_NUM3")
        private String AMT_NUM3;

        @JsonProperty("AMT_NUM4")
        private String AMT_NUM4;

        @JsonProperty("AMT_NUM7")
        private String AMT_NUM7;

        @JsonProperty("AMT_NUM8")
        private String AMT_NUM8;

        @JsonProperty("AMT_NUM14")
        private String AMT_NUM14;

        @JsonProperty("AMT_NUM24")
        private String AMT_NUM24;

        @JsonProperty("AMT_NUM25")
        private String AMT_NUM25;

        @JsonProperty("SUB_REF_CM")
        private String SUB_REF_CM;

        @JsonProperty("SUB_REF_NAME")
        private String SUB_REF_NAME;

        @JsonProperty("NUTRI_AMOUNT_SERVING")
        private String NUTRI_AMOUNT_SERVING;

        @JsonProperty("Z10500")
        private String Z10500;

        @JsonProperty("ITEM_REPORT_NO")
        private String ITEM_REPORT_NO;

        @JsonProperty("MAKER_NM")
        private String MAKER_NM;

        @JsonProperty("IMP_MANUFAC_NM")
        private String IMP_MANUFAC_NM;

        @JsonProperty("SELLER_MANUFAC_NM")
        private String SELLER_MANUFAC_NM;

        @JsonProperty("IMP_YN")
        private String IMP_YN;

        @JsonProperty("NATION_CM")
        private String NATION_CM;

        @JsonProperty("NATION_NM")
        private String NATION_NM;

        @JsonProperty("CRT_MTH_CD")
        private String CRT_MTH_CD;

        @JsonProperty("CRT_MTH_NM")
        private String CRT_MTH_NM;

        @JsonProperty("RESEARCH_YMD")
        private String RESEARCH_YMD;

        @JsonProperty("UPDATE_YMD")
        private String UPDATE_YMD;
    }
}