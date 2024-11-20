package com.citronix.citronix.repository;

import com.citronix.citronix.model.Farm;

import java.util.List;

public interface FarmRepositoryCustom {
    List<Farm> findAllFarmsBySurface(double surface);
    Farm findFarmByName(String name);
    List<Farm> findFarmsByNameAndLocation(String name , String location);
}
