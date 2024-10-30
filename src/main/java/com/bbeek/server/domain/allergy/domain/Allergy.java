package com.bbeek.server.domain.allergy.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    private Long userId;

    @Builder
    public Allergy(String info, Long userId) {
        this.info = info;
        this.userId = userId;
    }

}
