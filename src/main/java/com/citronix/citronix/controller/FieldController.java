package com.citronix.citronix.controller;

import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.service.FieldServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/fields")
public class FieldController {
    @Autowired
    private FieldServices fieldServices;

    @PostMapping("/create")
    public FieldDto createField(@RequestBody @Validated FieldDto fieldDto) {
        return fieldServices.create(fieldDto);
    }
    @PostMapping("/update")
    public FieldDto updateField(@RequestBody @Validated FieldDto fieldDto) {
        return fieldServices.update(fieldDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteField(@PathVariable UUID id) {
        if (fieldServices.delete(id)) {
            return ResponseEntity.ok("The field was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The field could not be deleted.");
        }
    }

}
