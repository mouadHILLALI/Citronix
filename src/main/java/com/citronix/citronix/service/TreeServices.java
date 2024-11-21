package com.citronix.citronix.service;

import com.citronix.citronix.dto.FieldDto;
import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.enums.TreeStatus;
import com.citronix.citronix.exceptions.EntitesCustomExceptions.NoFieldWasFound;
import com.citronix.citronix.helper.Validator;
import com.citronix.citronix.mapper.FieldMapper;
import com.citronix.citronix.mapper.TreeMapper;
import com.citronix.citronix.model.Field;
import com.citronix.citronix.model.Tree;
import com.citronix.citronix.repository.FieldRepository;
import com.citronix.citronix.repository.TreeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class TreeServices {
    @Autowired
    private TreeRepository treeRepository;
    @Autowired
    private TreeMapper treeMapper;
    @Autowired
    private FieldMapper fieldMapper;
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
            TreeStatus status = (treeDto.age() > 20) ? TreeStatus.NONPRODUCTIVE : TreeStatus.PRODUCTIVE;
            double productivity = validator.validateProductivity(treeDto);
            Tree tree = Tree.builder().age(treeDto.age()).plantationDate(treeDto.plantationDate()).productivity(productivity).status(status).field(field).build();
            return tree;
        }).toList();
        trees = treeRepository.saveAll(trees);
        return trees.stream().map(tree -> {
            FieldDto fieldDto = fieldMapper.fieldToFieldDto(tree.getField());
            TreeDto treeDto = new TreeDto(tree.getId() , tree.getAge() , tree.getPlantationDate() , tree.getProductivity() , tree.getStatus() , fieldDto);
            return treeDto;
        }).collect(Collectors.toList());
    }
    public TreeDto update(TreeDto treeDto) {
        Tree existingTree = treeRepository.findById(treeDto.id())
                .orElseThrow(() -> new NoFieldWasFound("No tree was found with this ID: " + treeDto.id()));
        Field field = fieldRepository.findById(treeDto.fieldDto().id())
                .orElseThrow(() -> new NoFieldWasFound("No field was found with this ID: " + treeDto.fieldDto().id()));
        validator.validatePlantationDate(treeDto);
        double productivity = validator.validateProductivity(treeDto);
        TreeStatus status = (treeDto.age() > 20) ? TreeStatus.NONPRODUCTIVE : TreeStatus.PRODUCTIVE;
        existingTree.setAge(treeDto.age());
        existingTree.setPlantationDate(treeDto.plantationDate());
        existingTree.setProductivity(productivity);
        existingTree.setStatus(status);
        existingTree.setField(field);
        Tree updatedTree = treeRepository.save(existingTree);
        FieldDto fieldDto = fieldMapper.fieldToFieldDto(updatedTree.getField());
        return new TreeDto(
                updatedTree.getId(),
                updatedTree.getAge(),
                updatedTree.getPlantationDate(),
                updatedTree.getProductivity(),
                updatedTree.getStatus(),
                fieldDto
        );
    }
    public void delete(UUID id){
        Tree tree = treeRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No tree was found with this Id : " + id));
        treeRepository.delete(tree);
    }
    public List<TreeDto> findAllTreesByStatus(TreeStatus status) {
        List<TreeDto> trees = treeRepository.findAllByStatus(status).stream().map(tree -> {
            FieldDto fieldDto = fieldMapper.fieldToFieldDto(tree.getField());
            TreeDto treeDto = new TreeDto(tree.getId() , tree.getAge() , tree.getPlantationDate() , tree.getProductivity() , tree.getStatus() , fieldDto);
            return treeDto;
        }).collect(Collectors.toList());
        return trees;
    }
}
