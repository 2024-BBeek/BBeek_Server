package com.bbeek.server.global.product.dto;

import lombok.Data;
import java.util.List;

@Data
public class BarcodeResponse {
    private String code;
    private String message;
    private String status;
    private String type;
    private String gtin;
    private String kanCode;
    private String clsTotalNm;
    private List<BaseItem> baseItems;
    private List<Company> companies;
    private List<String> brands;
    private List<Country> countries;
    private String prdComp;
    private String originVolume;
    private String prdPacTyp;
    private List<String> images;

    @Data
    public static class BaseItem {
        private String name;
        private String value;
    }

    @Data
    public static class Company {
        private String type;
        private String name;
        private List<String> addresses;
    }

    @Data
    public static class Country {
        private String name;
        private List<String> values;
    }
}