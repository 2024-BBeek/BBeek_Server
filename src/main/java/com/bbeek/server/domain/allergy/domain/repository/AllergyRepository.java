package com.bbeek.server.domain.allergy.domain.repository;

import com.bbeek.server.domain.allergy.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    Allergy findByUserId(Long userId);
}
