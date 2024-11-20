package com.citronix.citronix.service;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.exceptions.NoFarmWasFoundException;
import com.citronix.citronix.exceptions.NoFieldWasFound;
import com.citronix.citronix.mapper.FarmMapper;
import com.citronix.citronix.mapper.FieldMapper;
import com.citronix.citronix.model.Farm;
import com.citronix.citronix.model.Field;
import com.citronix.citronix.repository.FarmRepository;
import com.citronix.citronix.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FieldServices {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private FieldMapper fieldMapper;
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private FarmMapper farmMapper;
    public List<FieldDto> create(List<FieldDto> fieldDtos) {
        Farm farm = farmRepository.findById(fieldDtos.get(0).farmDto().id());
        if (farm == null) {
            throw new NoFarmWasFoundException(
                    "No farm was found with the following ID: " + fieldDtos.get(0).farmDto().id());
        }
        List<Field> fields = fieldDtos.stream().map(fieldDto -> {
            Field field = fieldMapper.fieldDtoToField(fieldDto);
            if (field.getSurface() == 0) {
                throw new NullPointerException("surface is null");
            }
            field.setSurface(field.getSurface());
            field.setFarm(farm);
            return field;
        }).collect(Collectors.toList());

        List<Field> savedFields = fieldRepository.saveAll(fields);
        List<FieldDto> savedFieldDtos = savedFields.stream().map(field -> {
            FarmDto farmDto = farmMapper.farmToFarmDto(field.getFarm());
            FieldDto newFieldDto = new FieldDto(field.getId() , field.getSurface() ,farmDto);
            return newFieldDto;
        }).collect(Collectors.toList());
       return savedFieldDtos;
    }
    public FieldDto update(FieldDto fieldDto) {
        Farm farm = farmRepository.findById(fieldDto.farmDto().id());
        if (farm == null) {
            throw new NoFarmWasFoundException("No farm was found with the following ID: " + fieldDto.farmDto().id());
        }
        Field field = fieldMapper.fieldDtoToField(fieldDto);
        field.setFarm(farm);
        field = fieldRepository.save(field);
        FarmDto farmDto = farmMapper.farmToFarmDto(field.getFarm());
        FieldDto newFieldDto = new FieldDto(field.getId() , field.getSurface() ,farmDto);
        return newFieldDto;
    }
    public boolean delete(UUID id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new NoFieldWasFound("No field was found with the following ID: " + id));
        fieldRepository.delete(field);
        return true;
    }

}
