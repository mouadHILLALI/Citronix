package com.citronix.citronix.service;

import com.citronix.citronix.dto.SaleDto;
import com.citronix.citronix.mapper.SaleMapper;
import com.citronix.citronix.model.Crop;
import com.citronix.citronix.model.Sale;
import com.citronix.citronix.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class SaleServices {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private SaleMapper saleMapper;
    public SaleDto create(SaleDto saleDto) {
        Sale sale = saleRepository.findSaleByCropId(saleDto.cropDto().id());
        if (sale != null) {
            throw new IllegalArgumentException("crop already sold");
        }
        double revenue = calcRevenue(saleDto);
        Crop crop = Crop.builder().id(saleDto.cropDto().id()).build();
        sale = Sale.builder().saleDate(LocalDate.now()).client(saleDto.client()).quantity(saleDto.quantity()).unitPrice(saleDto.unitPrice()).revenue(revenue).crop(crop).build();
        sale = saleRepository.save(sale);
        return saleMapper.saleToSaleDto(sale);
    }
    public double calcRevenue(SaleDto saleDto) {
        return saleDto.quantity() * saleDto.unitPrice();
    }
}
