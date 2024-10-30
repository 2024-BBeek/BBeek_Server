package com.bbeek.server.domain.barcode.service;

import com.bbeek.server.domain.barcode.presentation.dto.request.CreateBarcodeRequest;
import com.bbeek.server.domain.barcode.presentation.dto.response.ItemInfoResponse;
import com.bbeek.server.domain.barcode.service.implementation.BarcodeCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandBarcodeService {

    private BarcodeCreator barcodeCreator;

    public ItemInfoResponse create(CreateBarcodeRequest request, Long userId) {

        //바코드 번호로 물품 정보 가져오기
        //물품 저장 로직 (barcodeCreator.create(barcode);
        //AI 답변 요청 로직
        //답변을 ItemInfoResponse 형식으로 맞춰서 반환

        if (request.isIntaked()) {
            //섭취 했을 때 로직 - 유저 nutrition 에 값 추가
        } else {
            //미섭취 했을 때 로직
        }
    }
}
