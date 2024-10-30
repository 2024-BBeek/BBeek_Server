package com.bbeek.server.domain.barcode.service.implementation;

import com.bbeek.server.domain.barcode.domain.Barcode;
import com.bbeek.server.domain.barcode.domain.repository.BarcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarcodeCreator {

    private BarcodeRepository barcodeRepository;

    public void create(Barcode barcode) {
        barcodeRepository.save(barcode);
    }
}
