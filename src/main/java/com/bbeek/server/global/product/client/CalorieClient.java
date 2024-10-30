package com.bbeek.server.global.product.client;

import com.bbeek.server.global.product.dto.CalorieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CalorieClient", url = "${product.calorie.url}")
public interface CalorieClient {

    @GetMapping("/getFoodNtrCpntDbInq01")
    CalorieResponse toCalorie(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("type") String type,
            @RequestParam("FOOD_NM_KR") String foodName
    );
}