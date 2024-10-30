package com.bbeek.server.domain.barcode.presentation;

import com.bbeek.server.domain.barcode.presentation.dto.request.CreateBarcodeRequest;
import com.bbeek.server.domain.barcode.presentation.dto.response.ItemInfoResponse;
import com.bbeek.server.domain.barcode.service.CommandBarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bbeek.server.global.jwt.util.AuthenticationUtil.getId;

@RestController
@RequiredArgsConstructor
public class BarcodeController {

    private CommandBarcodeService commandBarcodeService;

    @PostMapping("/barcodes")
    public ItemInfoResponse createBarcode(@RequestBody CreateBarcodeRequest request) {
        return commandBarcodeService.create(request, getId());
    }
}
