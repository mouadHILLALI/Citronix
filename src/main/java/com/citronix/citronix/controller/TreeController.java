package com.citronix.citronix.controller;

import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.enums.TreeStatus;
import com.citronix.citronix.service.TreeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trees")
public class TreeController {
    @Autowired
    private TreeServices treeServices;
    @PostMapping("/create")
    public List<TreeDto> createTrees(@RequestBody @Validated List<TreeDto> trees) {
        return treeServices.createTree(trees);
    }
    @PostMapping("/update")
    public TreeDto updateTree(@RequestBody @Validated TreeDto tree) {
        return treeServices.update(tree);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTree(@PathVariable UUID id) {
        treeServices.delete(id);
        return ResponseEntity.ok("Tree was deleted successfully");
    }
    @GetMapping("/getAll/{status}")
    public List<TreeDto> getAll(@PathVariable TreeStatus status) {
        return treeServices.findAllTreesByStatus(status);
    }
}
