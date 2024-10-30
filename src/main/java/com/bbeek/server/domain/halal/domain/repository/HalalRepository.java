package com.bbeek.server.domain.halal.domain.repository;

import com.bbeek.server.domain.halal.domain.Halal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HalalRepository extends JpaRepository<Halal, Long> {
    List<Halal> findByUserId(Long userId);
}
