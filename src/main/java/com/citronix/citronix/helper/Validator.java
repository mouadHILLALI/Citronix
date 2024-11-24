package com.citronix.citronix.helper;

import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.FieldSurfaceException;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.FieldsGeneralException;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.IllegalFieldsNumber;
import org.springframework.stereotype.Component;

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
}
