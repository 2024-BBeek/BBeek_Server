package com.bbeek.server.domain.allergy.domain.repository;

import com.bbeek.server.domain.allergy.domain.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findByUserId(Long userId);
}
