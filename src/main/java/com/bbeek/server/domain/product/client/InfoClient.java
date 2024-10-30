package com.bbeek.server.domain.product.client;

import com.bbeek.server.domain.product.dto.InfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "InfoClient", url = "${product.info.url}")
public interface InfoClient {

    //PRDLST_NM=
    @GetMapping("PRDLST_NM={name}")
    InfoResponse toInfo(
            @PathVariable String name
    );
}
