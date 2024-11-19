package com.citronix.citronix.service;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.mapper.FarmMapper;
import com.citronix.citronix.model.Farm;
import com.citronix.citronix.repository.FarmRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class FarmServices {
    @Autowired
    private FarmRepository farmRepository;

   @Autowired
   private FarmMapper farmMapper;

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
    public void deleteFarm(UUID id) {
        Farm fetchedFarm = farmRepository.findById(id);
        if (id == null || fetchedFarm == null) {
            throw new NullPointerException("farm was not deleted");
        }
        fetchedFarm.setDeletedAt(LocalDate.now());
        farmRepository.save(fetchedFarm);
    }
}
