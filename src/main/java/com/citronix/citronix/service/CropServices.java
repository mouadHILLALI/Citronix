package com.citronix.citronix.service;

import com.citronix.citronix.dto.CropDto;
import com.citronix.citronix.dto.HarvestDto;
import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.helper.Validator;
import com.citronix.citronix.mapper.CropMapper;
import com.citronix.citronix.mapper.TreeMapper;
import com.citronix.citronix.model.Crop;
import com.citronix.citronix.model.Harvest;
import com.citronix.citronix.model.Tree;
import com.citronix.citronix.repository.CropRepository;
import com.citronix.citronix.repository.HarvestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CropServices {
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private Validator validator;
    @Autowired
    private HarvestRepository harvestRepository;
    @Autowired
    private TreeMapper treeMapper;
    public CropDto harvest(List<HarvestDto> harvestDtos) {
        validator.validateTreeHarvestPerSeason(harvestDtos);
        validator.validateHarvestSeason(harvestDtos);

        double totalQuantity = harvestDtos.stream()
                .mapToDouble(HarvestDto::quantity)
                .sum();

        List<Tree> trees = harvestDtos.stream()
                .map(harvestDto -> Tree.builder().id(harvestDto.treeDto().id()).build())
                .toList();

        Crop crop = Crop.builder()
                .quantity(totalQuantity)
                .harvestDate(harvestDtos.get(0).harvestDate())
                .season(harvestDtos.get(0).season())
                .trees(trees)
                .build();
        crop = cropRepository.save(crop);

        Crop finalCrop = crop;
        List<Harvest> harvests = harvestDtos.stream()
                .map(harvestDto -> Harvest.builder()
                        .tree(Tree.builder().id(harvestDto.treeDto().id()).build())
                        .crop(finalCrop)
                        .harvestDate(harvestDto.harvestDate())
                        .season(harvestDto.season())
                        .quantity(harvestDto.quantity())
                        .build())
                .toList();
        harvests = harvestRepository.saveAll(harvests);

        List<TreeDto> treeDtos = harvests.stream()
                .map(harvest -> treeMapper.treeToTreeDto(harvest.getTree()))
                .toList();
        return new CropDto(
                crop.getId(),
                crop.getQuantity(),
                crop.getHarvestDate(),
                crop.getSeason(),
                treeDtos
        );
    }
}
