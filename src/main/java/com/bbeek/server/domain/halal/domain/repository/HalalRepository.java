package com.bbeek.server.domain.halal.domain.repository;

import com.bbeek.server.domain.halal.domain.Halal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HalalRepository extends JpaRepository<Halal, Long> {
}
