package com.bbeek.server.domain.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class InfoResponse {
    @JsonProperty("C002")
    private C002 c002;

    @Data
    public static class C002 {
        @JsonProperty("total_count")
        private String totalCount;

        @JsonProperty("row")
        private List<Row> row;

        @JsonProperty("RESULT")
        private Result result;
    }

    @Data
    public static class Row {
        @JsonProperty("PRDLST_REPORT_NO")
        private String prdlstReportNo;

        @JsonProperty("PRMS_DT")
        private String prmsDt;

        @JsonProperty("LCNS_NO")
        private String lcnsNo;

        @JsonProperty("PRDLST_NM")
        private String prdlstNm;

        @JsonProperty("BSSH_NM")
        private String bsshNm;

        @JsonProperty("PRDLST_DCNM")
        private String prdlstDcnm;

        @JsonProperty("CHNG_DT")
        private String chngDt;

        @JsonProperty("RAWMTRL_NM")
        private String rawmtrlNm;
    }

    @Data
    public static class Result {
        @JsonProperty("MSG")
        private String msg;

        @JsonProperty("CODE")
        private String code;
    }
}