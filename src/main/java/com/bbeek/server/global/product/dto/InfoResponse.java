package com.bbeek.server.global.product.dto;

import lombok.Data;
import java.util.List;

@Data
public class InfoResponse {
    private C002 c002;

    @Data
    public static class C002 {
        private String total_count;
        private List<Row> row;
        private Result result;
    }

    @Data
    public static class Row {
        private String prdlst_report_no;
        private String prms_dt;
        private String lcns_no;
        private String prdlst_nm;
        private String bssh_nm;
        private String prdlst_dcnm;
        private String chng_dt;
        private String rawmtrl_nm;
    }

    @Data
    public static class Result {
        private String msg;
        private String code;
    }
}