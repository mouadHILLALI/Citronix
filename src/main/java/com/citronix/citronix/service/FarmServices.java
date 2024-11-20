package com.citronix.citronix.service;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.NoFarmWasFoundException;
import com.citronix.citronix.mapper.FarmMapper;
import com.citronix.citronix.model.Farm;
import com.citronix.citronix.repository.FarmRepository;
import com.citronix.citronix.repository.Impl.FarmRepositoryImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class FarmServices {
    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private FarmMapper farmMapper;
    @Autowired
    private FarmRepositoryImpl farmRepositoryImpl;

    public FarmDto createFarm(FarmDto farmDto) {
        if (farmDto == null) {
            throw new NullPointerException("farmDto is null");
        }
        Farm farm = farmMapper.farmDtoToFarm(farmDto);
        farm.setCreationTime(LocalDate.now());
      return farmMapper.farmToFarmDto(farmRepository.save(farm));
    }
    public FarmDto updateFarm(FarmDto farmDto) {
        if (farmDto == null) {
            throw new NullPointerException("farmDto is null");
        }
       return farmMapper.farmToFarmDto(farmRepository.save(farmMapper.farmDtoToFarm(farmDto)));
    }
    public boolean deleteFarm(UUID id) {
        Farm fetchedFarm = farmRepository.findById(id);
        if (id == null || fetchedFarm == null) {
            throw new NoFarmWasFoundException("No farm was found with id " + id);
        }
        fetchedFarm.setDeletedAt(LocalDate.now());
        farmRepository.save(fetchedFarm);
        return true;
    }
    public List<FarmDto> getAllFarmsBySurface(double surface) {
        List<Farm> Farms = farmRepositoryImpl.findAllFarmsBySurface(surface);
        List<FarmDto> farmDtos = Farms.stream().map(farm -> {
            FarmDto farmDto = farmMapper.farmToFarmDto(farm);
            return farmDto;
        }).collect(Collectors.toList());
        return farmDtos;
    }
    public FarmDto getFarmByName(String farmName) {
        FarmDto farmDto = farmMapper.farmToFarmDto(farmRepositoryImpl.findFarmByName(farmName));
        if (farmDto == null) {
            throw new NoFarmWasFoundException("No farm was found with name " + farmName);
        }
        return farmDto;
    }
    public List<FarmDto> getAllFarmsByNameAndLocation(String farmName, String location) {
        List<FarmDto> farmDtos = farmRepositoryImpl.findFarmsByNameAndLocation(farmName,location).stream().map(farm -> {
            FarmDto farmDto = farmMapper.farmToFarmDto(farm);
            return farmDto;
        }).collect(Collectors.toList());
        if (farmDtos == null) {
            throw new NoFarmWasFoundException("No farms were found with the search criteria:\n name: " + farmName + " \n location: " + location);
        }
        return farmDtos;
    }
}
