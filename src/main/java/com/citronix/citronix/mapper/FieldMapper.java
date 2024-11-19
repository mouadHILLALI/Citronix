package com.citronix.citronix.mapper;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.model.Farm;
import com.citronix.citronix.model.Field;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    FieldDto fieldToFieldDto(Field field);
    Field fieldDtoToField(FieldDto fieldDto);
}
