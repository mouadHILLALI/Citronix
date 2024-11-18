package com.citronix.citronix.mapper;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.model.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmDto farmToFarmDto(Farm farm);
    Farm farmDtoToFarm(FarmDto farmDto);
}
