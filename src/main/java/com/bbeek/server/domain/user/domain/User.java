package com.bbeek.server.domain.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String password;

    private String username;

    private String vegetarianType;

    private Integer age;

    private String gender;

    private Integer height;

    private Integer weight;

    @Builder(builderMethodName = "normalUserBuilder")
    public User(String userId, String username, String password, String vegetarianType, Integer age, String gender, Integer height, Integer weight) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.vegetarianType = vegetarianType;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
