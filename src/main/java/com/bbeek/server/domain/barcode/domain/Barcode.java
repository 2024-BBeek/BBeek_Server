package com.bbeek.server.domain.barcode.domain;

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
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Boolean isIntaked;

    private Long userId;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Barcode(String code, Boolean isIntaked, Long userId) {
        this.code = code;
        this.isIntaked = isIntaked;
        this.userId = userId;
    }
}
