package com.bbeek.server.domain.barcode.domain.repository;

import com.bbeek.server.domain.barcode.domain.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
}
