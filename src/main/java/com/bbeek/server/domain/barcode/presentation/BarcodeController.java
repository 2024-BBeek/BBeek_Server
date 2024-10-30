package com.bbeek.server.domain.barcode.presentation;

import com.bbeek.server.domain.barcode.presentation.dto.response.ItemInfoResponse;
import com.bbeek.server.domain.barcode.service.CommandBarcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//import static com.bbeek.server.global.jwt.util.AuthenticationUtil.getId;

@RestController
@RequiredArgsConstructor
public class BarcodeController {

    private final CommandBarcodeService commandBarcodeService;

    @GetMapping("/barcodes/{code}")
    public ItemInfoResponse createBarcode(@PathVariable String code) {
        return commandBarcodeService.create(code);
    }
}
