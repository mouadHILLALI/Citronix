package com.citronix.citronix.controller;

import com.citronix.citronix.dto.SaleDto;
import com.citronix.citronix.service.SaleServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private SaleServices saleServices;

    @PostMapping("/create")
    public SaleDto createSale(@RequestBody @Valid SaleDto saleDto) {
        return saleServices.create(saleDto);
    }
}
