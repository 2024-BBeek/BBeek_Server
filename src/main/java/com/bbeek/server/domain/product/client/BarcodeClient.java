package com.bbeek.server.domain.product.client;

import com.bbeek.server.domain.product.dto.BarcodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nameClient", url = "${product.barcode.url}")
public interface BarcodeClient {

    @GetMapping("{code}")
    BarcodeResponse toName(
            @PathVariable("code") String code
    );

}
