package com.citronix.citronix.mapper;

import com.citronix.citronix.dto.CropDto;
import com.citronix.citronix.model.Crop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropMapper {
    Crop cropDtoToCrop(CropDto cropDto);
    CropDto cropToCropDto(Crop crop);
}
