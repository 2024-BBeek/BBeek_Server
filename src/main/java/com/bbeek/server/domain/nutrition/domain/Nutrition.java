package com.bbeek.server.domain.nutrition.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double protein; // 단백질

    private Double province; // 지방

    private Double carbohydrate; // 탄수화물

    private Double sugar; // 당류

    private Double sodium; // 나트륨

    private Double cholesterol; // 콜레스테롤

    private Double pFat; // 포화지방

    private Double tFat; // 트렌스지방

    @CreatedDate
    private LocalDateTime recordDate;

    @Builder
    public Nutrition(Long userId, Double protein, Double province, Double carbohydrate, Double sugar, Double sodium, Double cholesterol, Double pFat, Double tFat) {
        this.userId = userId;
        this.protein = protein;
        this.province = province;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.sodium = sodium;
        this.cholesterol = cholesterol;
        this.pFat = pFat;
        this.tFat = tFat;
    }
}
