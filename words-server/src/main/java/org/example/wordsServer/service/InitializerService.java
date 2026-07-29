package org.example.wordsServer.service;

import lombok.AllArgsConstructor;
import org.example.wordsServer.repository.WordsRepository;
import org.example.wordsServer.schema.Node;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitializerService {
    private WordsRepository wordsRepository;

    public void initializeWordsNode() {
        Node root = wordsRepository.getRootNode();
        if (root == null) {
            Node rootNode = Node.builder()
                    .character('\0')
                    .isRoot(true)
                    .isTerminal(false)
                    .build();
            wordsRepository.save(rootNode);
        }
    }

}
