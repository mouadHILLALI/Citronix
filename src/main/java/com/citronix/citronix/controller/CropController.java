package com.citronix.citronix.controller;

import com.citronix.citronix.dto.CropDto;
import com.citronix.citronix.dto.HarvestDto;
import com.citronix.citronix.service.CropServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {
    @Autowired
    private CropServices cropServices;

    @PostMapping("/harvest")
    public CropDto harvest(@RequestBody @Validated List<HarvestDto> harvests) {
        return cropServices.harvest(harvests);
    }
}
