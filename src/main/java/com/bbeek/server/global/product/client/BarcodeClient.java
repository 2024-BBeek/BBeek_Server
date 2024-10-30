package com.bbeek.server.global.product.client;

import com.bbeek.server.global.product.dto.BarcodeResponse;
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
