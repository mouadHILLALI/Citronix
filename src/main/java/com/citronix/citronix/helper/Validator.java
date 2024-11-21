package com.citronix.citronix.helper;

import com.citronix.citronix.dto.CropDto;
import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.dto.HarvestDto;
import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.enums.SeasonEnum;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.*;
import com.citronix.citronix.model.Harvest;
import com.citronix.citronix.model.Tree;
import com.citronix.citronix.repository.HarvestRepository;
import com.citronix.citronix.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class Validator {
    @Autowired
    private HarvestRepository harvestRepository;
    @Autowired
    private TreeRepository treeRepository;
    public boolean validateFieldSurfaceVsFarmSurface(double farmSurface, double fieldSurface) {
        if (fieldSurface > farmSurface * 0.5) {
            throw new FieldSurfaceException(
                    "Field surface: " + fieldSurface + " cannot be more than 50% of the farm surface: " + farmSurface
            );
        }
        return true;
    }
    public boolean validateNumberOfFieldsPerFarm(List<FieldDto> fields) {
        if (fields.size() > 10) {
            throw new IllegalFieldsNumber("number of fields per farm: " + fields.size()+",a farm must not exceed 10 fields");
        }
        return true;
    }
    public boolean totalSurfaceVsFarm(double farmSurface, List<FieldDto> fields) {
        double totalSurface = fields.stream().mapToDouble(field -> field.surface()).sum();
        if (totalSurface > farmSurface) {
            throw new FieldsGeneralException("fields total surface cannot be more than farm surface: " + totalSurface + "square meters");
        }
        return true;
    }
    public boolean validateFieldTreesDensity(double fieldSurface ,List<TreeDto> trees) {
         final double treeDensity = 100 ;
         double allowedNumberOfTrees = fieldSurface / treeDensity;
         if (allowedNumberOfTrees < trees.size()) {
             throw new TreesDensityException("Field cannot contain more than this number of trees: " + allowedNumberOfTrees);
         }
         return true;
    }
    public boolean validatePlantationDate(TreeDto tree) {
        if (tree.plantationDate() == null) {
            throw new IllegalArgumentException("Plantation date cannot be null.");
        }
        int months = tree.plantationDate().getMonth().getValue();
        if (months > 2 && months < 6) {
            return true;
        }
        throw new IllegalPlantationDate("Tree with the age:"+tree.age()+" cannot be planted in :" + tree.plantationDate() );
    }
    public double validateProductivity(TreeDto tree) {
        double productivity = 0;
        if (tree.age()<3){
            productivity = 2.5;
        } else if (tree.age()>3 &&tree.age()<10) {
            productivity = 12;
        } else {
            productivity = 20;
        }
        return productivity;
    }
    public boolean validateHarvestSeason(List<HarvestDto> harvestDtos) {
        for (HarvestDto harvest : harvestDtos) {
            int month = harvest.harvestDate().getMonthValue();
            switch (harvest.season()) {
                case WINTER:
                    if (!(month == 12 || month == 1 || month == 2)) {
                        throw new IllegalPlantationDate("Harvest cannot be in winter. Harvest date: " + harvest.harvestDate());
                    }
                    break;
                case SPRING:
                    if (!(month >= 3 && month <= 5)) {
                        throw new IllegalPlantationDate("Harvest cannot be in spring. Harvest date: " + harvest.harvestDate());
                    }
                    break;
                case SUMMER:
                    if (!(month >= 6 && month <= 8)) {
                        throw new IllegalPlantationDate("Harvest cannot be in summer. Harvest date: " + harvest.harvestDate());
                    }
                    break;
                case FALL:
                    if (!(month >= 9 && month <= 11)) {
                        throw new IllegalPlantationDate("Harvest cannot be in fall. Harvest date: " + harvest.harvestDate());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid season: " + harvest.season());
            }
        }
        return true;
    }
    public boolean validateTreeHarvestPerSeason(List<HarvestDto> harvestDtos){
        harvestDtos.stream().forEach(harvestDto ->{
            Tree tree = treeRepository.findById(harvestDto.treeDto().id()).orElseThrow(()-> new NoSuchElementException("No tree exist with the following Id :" + harvestDto.treeDto().id()));
            Harvest harvest = harvestRepository.findByTreeAndSeason(tree , harvestDto.season());
            if (harvest != null) {
                throw new IllegalHarvestDate("the tree was already harvested  :" + tree.getId() + "in this season" + harvestDto.season() );
            }
        });
        return true;
    }
}
