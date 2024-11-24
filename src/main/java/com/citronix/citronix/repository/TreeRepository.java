package com.citronix.citronix.repository;

import com.citronix.citronix.enums.TreeStatus;
import com.citronix.citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {
    List<Tree> findAllByStatus(TreeStatus status);
}
