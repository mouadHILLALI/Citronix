package com.citronix.citronix.controller;

import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.service.TreeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trees")
public class TreeController {
    @Autowired
    private TreeServices treeServices;
    @PostMapping("/create")
    public List<TreeDto> createTrees(@RequestBody @Validated List<TreeDto> trees) {
        return treeServices.createTree(trees);
    }
}
