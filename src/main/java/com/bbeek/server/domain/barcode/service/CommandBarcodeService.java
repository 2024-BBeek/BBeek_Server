package com.bbeek.server.domain.barcode.service;

import com.bbeek.server.domain.ai.service.AiService;
import com.bbeek.server.domain.allergy.domain.Allergy;
import com.bbeek.server.domain.allergy.domain.repository.AllergyRepository;
import com.bbeek.server.domain.barcode.domain.Barcode;
import com.bbeek.server.domain.barcode.presentation.dto.response.ItemInfoResponse;
import com.bbeek.server.domain.barcode.service.implementation.BarcodeCreator;
import com.bbeek.server.domain.product.dto.ProductDto;
import com.bbeek.server.domain.product.presentation.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandBarcodeService {

    private BarcodeCreator barcodeCreator;
    private final ProductService productService;
    private final AiService aiService;
    private final AllergyRepository allergyRepository;

    public ItemInfoResponse create(String code, Long userId) {

        //바코드 번호로 물품 정보 가져오기
        ProductDto productDto = productService.barcodeToName(code);
        //조회한거 저장 로직
//        barcodeCreator.create(
//                Barcode.builder()
//                        .code(code)
//                        .userId(userId)
//                        .build()
//        );
        //AI 답변 요청 로직
        List<Allergy> allergy = allergyRepository.findByUserId(userId);
        List<String> checkAllergy = aiService.handleAllergyRequest(allergy.toString() , productDto.getMaterials());
        //답변을 ItemInfoResponse 형식으로 맞춰서 반환
        return new ItemInfoResponse(checkAllergy, productDto);
    }
}