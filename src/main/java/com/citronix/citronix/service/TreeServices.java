package com.citronix.citronix.service;

import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.enums.TreeStatus;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.NoFieldWasFound;
import com.citronix.citronix.helper.Validator;
import com.citronix.citronix.mapper.TreeMapper;
import com.citronix.citronix.model.Field;
import com.citronix.citronix.model.Tree;
import com.citronix.citronix.repository.FieldRepository;
import com.citronix.citronix.repository.TreeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TreeServices {
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private TreeMapper treeMapper;
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private Validator validator;

    public List<TreeDto> createTree(List<TreeDto> treeDtos) {
        Field field = fieldRepository.findById(treeDtos.get(0).fieldDto().id()).orElseThrow(()-> new NoFieldWasFound("no field was found with this Id : " + treeDtos.get(0).fieldDto().id()));
        validator.validateFieldTreesDensity(field.getSurface() , treeDtos);
        List<Tree> trees = treeDtos.stream().map(treeDto -> {
            validator.validatePlantationDate(treeDto);
            validator.validateProductivity(treeDto);
            TreeStatus status = null;
            if (treeDto.age()>20){
                status = TreeStatus.NONPRODUCTIVE;
            }else{
                status = TreeStatus.PRODUCTIVE;
            }
            Tree tree = new Tree(null , treeDto.age() , treeDto.plantationDate() , treeDto.productivity() , status,field );
            return tree;
        }).toList();
        trees = treeRepository.saveAll(trees);
        List<TreeDto> treesDtos = trees.stream().map(tree -> {
            TreeDto treeDto = treeMapper.treeToTreeDto(tree);
            return treeDto;
        }).toList();
        return treesDtos;
    }
}
