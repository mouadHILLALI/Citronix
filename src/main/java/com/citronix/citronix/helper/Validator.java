package com.citronix.citronix.helper;

import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Validator {

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
}
