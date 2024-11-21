package com.citronix.citronix.mapper;

import com.citronix.citronix.dto.TreeDto;
import com.citronix.citronix.model.Tree;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FieldMapper.class)
public interface TreeMapper {
    Tree treeDtoToTree(TreeDto dto);
    TreeDto treeToTreeDto(Tree tree);
}
