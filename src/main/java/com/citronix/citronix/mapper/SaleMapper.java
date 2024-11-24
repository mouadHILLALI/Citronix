package com.citronix.citronix.mapper;

import com.citronix.citronix.dto.SaleDto;
import com.citronix.citronix.model.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" , uses = {
        CropMapper.class
})
public interface SaleMapper {
    Sale saleDtoToSale(SaleDto saleDto);
    SaleDto saleToSaleDto(Sale sale);
}
