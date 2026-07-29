package org.example.wordsServer.repository;

import org.example.wordsServer.schema.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsRepository extends JpaRepository<Node, Long> {
    @Query("SELECT node FROM Node node WHERE node.isRoot = true")
    Node getRootNode();
}
