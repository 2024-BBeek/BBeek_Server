package com.bbeek.server.domain.product.dto;

import lombok.Data;


@Data
public class ProductDto {

    private String productName; // 제품 이름
    private String productImg; // 제품 이미지
    private Double protein; // 단백질
    private Double province; // 지방
    private Double carbohydrate; // 탄수화물
    private Double sugar; // 당류
    private Double sodium; // 나트륨
    private Double cholesterol; // 콜레스테롤
    private Double pFat; // 포화지방
    private Double tFat; // 트렌스지방
    private String materials; // 원재료

    public ProductDto(String productName, String productImg, Double protein, Double province, Double carbohydrate, Double sugar, Double sodium, Double cholesterol, Double pFat, Double tFat, String materials) {
        this.productName = productName;
        this.productImg = productImg;
        this.protein = protein;
        this.province = province;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.sodium = sodium;
        this.cholesterol = cholesterol;
        this.pFat = pFat;
        this.tFat = tFat;
        this.materials = materials;
    }
}
