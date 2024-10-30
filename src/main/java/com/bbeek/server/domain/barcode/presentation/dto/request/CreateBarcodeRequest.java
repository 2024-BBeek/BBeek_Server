package com.bbeek.server.domain.barcode.presentation.dto.request;

public record CreateBarcodeRequest(
        String code,
        Boolean isIntaked
) {
}
