package com.citronix.citronix.controller;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.service.FarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/farms")
public class FarmController {
    @Autowired
    private FarmServices farmServices;

    @PostMapping("/create")
    public FarmDto addFarm(@RequestBody FarmDto farmDto) {
        return farmServices.createFarm(farmDto);
    }
    @PostMapping("/update")
    public FarmDto updateFarm(@RequestBody FarmDto farmDto) {
        return farmServices.updateFarm(farmDto);
    }
    @GetMapping("/delete/{id}")
    public void deleteFarm(@PathVariable UUID id) {
        farmServices.deleteFarm(id);
    }

}
