package com.bbeek.server.domain.product.presentation;

import com.bbeek.server.domain.product.client.BarcodeClient;
import com.bbeek.server.domain.product.client.CalorieClient;
import com.bbeek.server.domain.product.client.InfoClient;
import com.bbeek.server.domain.product.dto.BarcodeResponse;
import com.bbeek.server.domain.product.dto.CalorieResponse;
import com.bbeek.server.domain.product.dto.InfoResponse;
import com.bbeek.server.domain.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${product.calorie.key}")
    private String calorieKey;

    private final BarcodeClient barcodeClient;
    private final CalorieClient calorieClient;
    private final InfoClient infoClient;

    private ProductDto toProductDto(String name, String img, CalorieResponse.Item item, InfoResponse.Row row){
        return new ProductDto(
                name,
                img,
                toDouble(item.getAMT_NUM3()),
                toDouble(item.getAMT_NUM4()),
                toDouble(item.getAMT_NUM7()),
                toDouble(item.getAMT_NUM8()),
                toDouble(item.getAMT_NUM14()),
                toDouble(item.getAMT_NUM24()),
                toDouble(item.getAMT_NUM25()),
                toDouble(item.getAMT_NUM26()),
                row.getRawmtrlNm()
        );
    }

    private Double toDouble(String value){
        if (value.isEmpty()) return 0.0;
        return Double.parseDouble(value);
    }

    //바코드 정보로 제품 이름 조회
    public ProductDto barcodeToName(String code) {
        BarcodeResponse barcode = barcodeClient.toName(code);
        String name = barcode.getBaseItems().get(0).getValue();
        String img = barcode.getImages().get(0);
        return nameToCalorieAndInfo(name, img);
    }

    // 제품 이름으로 제품 칼로리 및 정보 조회
    private ProductDto nameToCalorieAndInfo(String name, String img) {
        CalorieResponse calorieResponse = calorieClient.toCalorie(
                calorieKey,
                1,
                1,
                "json",
                name
        );

        CalorieResponse.Item item = calorieResponse.getBody().getItems().get(0);


        InfoResponse infoResponse = infoClient.toInfo(
                name
        );

        InfoResponse.Row row = infoResponse.getC002().getRow().get(0); // 원재료 종류
        return toProductDto(name, img, item, row);

    }



}
