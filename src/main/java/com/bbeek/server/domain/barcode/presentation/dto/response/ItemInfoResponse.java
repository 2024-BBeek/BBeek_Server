package com.bbeek.server.domain.barcode.presentation.dto.response;

import com.bbeek.server.domain.product.dto.ProductDto;

import java.util.List;


public record ItemInfoResponse(
        List<String> allergy, //
        ProductDto productDto
) {
}
