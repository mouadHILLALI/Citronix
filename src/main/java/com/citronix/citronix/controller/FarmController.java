package com.citronix.citronix.controller;

import com.citronix.citronix.dto.FarmDto;
import com.citronix.citronix.service.FarmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/farms")
public class FarmController {
    @Autowired
    private FarmServices farmServices;

    @PostMapping("/create")
    public FarmDto addFarm(@RequestBody @Validated FarmDto farmDto) {
        return farmServices.createFarm(farmDto);
    }
    @PostMapping("/update")
    public FarmDto updateFarm(@RequestBody @Validated FarmDto farmDto) {
        return farmServices.updateFarm(farmDto);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteFarm(@PathVariable UUID id) {
        if (farmServices.deleteFarm(id)){
            return ResponseEntity.ok("Farm deleted");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Farm not deleted");
        }
    }
    @GetMapping("/searchSurface/{surface}")
    public List<FarmDto> searchFarm(@PathVariable double surface) {
        return farmServices.getAllFarmsBySurface(surface);
    }
    @GetMapping("/searchName/{name}")
    public FarmDto searchFarmByName(@PathVariable String name) {
        return farmServices.getFarmByName(name);
    }
    @GetMapping("/searchLocation/{name}/{location}")
    public List<FarmDto> searchFarmByNameAndLocation(@PathVariable String name, @PathVariable String location) {
        return farmServices.getAllFarmsByNameAndLocation(name ,location);
    }

}
