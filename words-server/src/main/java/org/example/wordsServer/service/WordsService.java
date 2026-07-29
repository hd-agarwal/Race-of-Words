package org.example.wordsServer.service;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.example.wordsServer.repository.WordsRepository;
import org.example.wordsServer.schema.Node;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class WordsService {
    private WordsRepository wordsRepository;

    private Node getRootNode() {
        Node root = wordsRepository.getRootNode();
        if (root == null) {
            throw new RuntimeException("Root node not yet configured in database.");
        }
        return root;
    }
    public void addWord(String word) {
        Node current = getRootNode();
        word = word.toLowerCase();
        word = word.trim();
        for (int i = 0; i < word.length(); i++) {
            // For each character of the word
            Character ch = word.charAt(i);
            // Find the id of the child node with that character
            Long nextId = Node.getChildNodeId(current, ch);
            Node nextNode;
            // If the current node does not have child with that character
            if (nextId == null) {
                // Create the next node
                nextNode = Node.builder()
                        .character(ch)
                        .isRoot(false)
                        .isTerminal(i == word.length() - 1)
                        .count(i == word.length() - 1 ? 1L: null)
                        .build();
                // Save this node in DB
                nextNode = wordsRepository.save(nextNode);
                // Update the child index for this node
                Node.setChildNodeId(current, ch, nextNode.getId());
                // Update the current node in DB
                wordsRepository.save(current);
            } else {
                // Get the child node from DB
                Node finalCurrent = current;
                nextNode = wordsRepository.findById(nextId).orElseThrow(() -> new RuntimeException("Severe issue: Breakage in Trie in DB\nParent Node Id: " + finalCurrent.getId() + "; Parent.childID: " + nextId + "; But node with id " + nextId + " does not exist in DB"));
                // In case the next letter is the last one
                if (i == word.length() - 1) {
                    // Update isTerminal on the child node
                    nextNode.setIsTerminal(true);
                    // If count was never present (not terminal earlier), set to 1, otherwise increase 1
                    nextNode.setCount(nextNode.getCount() == null ? 1 : nextNode.getCount() + 1);
                    // Save the child in DB
                    nextNode = wordsRepository.save(nextNode);
                }
            }
            current = nextNode;

        }
    }

    public Long getWordFrequency(String word) {
        Node current = getRootNode();
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            Long childId = Node.getChildNodeId(current, ch);

            if (childId == null) {
                return 0L;
            }
            Node finalCurrent = current;
            current = wordsRepository.findById(childId).orElseThrow(() -> new RuntimeException("Severe issue: Breakage in Trie in DB\nParent Node Id: " + finalCurrent.getId() + "; Parent.childID: " + childId + "; But node with id " + childId + " does not exist in DB"));

        }
        if (!current.getIsTerminal()) {
            return 0L;
        }
        return current.getCount();
    }

    public Boolean deleteWord(String word) {
        return deleteWord(word, 1L);
    }

    public Boolean deleteWord(String word, Long count) {
        Node current = getRootNode();
        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            Long childId = Node.getChildNodeId(current, ch);

            if (childId == null) {
                return false;
            }
            Node finalCurrent = current;
            current = wordsRepository.findById(childId).orElseThrow(() -> new RuntimeException("Severe issue: Breakage in Trie in DB\nParent Node Id: " + finalCurrent.getId() + "; Parent.childID: " + childId + "; But node with id " + childId + " does not exist in DB"));

        }
        if (!current.getIsTerminal()) {
            return false;
        }
        Long currentCount = current.getCount();
        if (currentCount.equals(0L)) {
            return false;
        }
        current.setCount(Math.max(0, current.getCount() - count));
        wordsRepository.save(current);
        return true;
    }

    private Map<String, Long> getAllWords() {
        Map<String, Long> allWords = new HashMap<>();
        Node root = getRootNode();
        populateAllWords(root, allWords, "");
        return allWords;
    }

    public Pair<Long, Map<String, Long>> getAllWordsWithTotalCount() {
        Map<String, Long> allWords = getAllWords();
        Long totalCount = 0L;
        for (Map.Entry<String, Long> entry: allWords.entrySet()) {
            totalCount += entry.getValue();
        }
        return new Pair<>(totalCount, allWords);
    }

    private void populateAllWords(Node root, Map<String, Long> allWords, String currWord) {
        if (root == null) {
            return;
        }
        if (root.getIsTerminal() && root.getCount() > 0L) {
            allWords.put(currWord, root.getCount());
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            Long childId = Node.getChildNodeId(root, ch);
            if (childId != null) {
                Node child = wordsRepository.findById(childId).orElseThrow(() -> new RuntimeException("Severe issue: Breakage in Trie in DB\nParent Node Id: " + root.getId() + "; Parent.childID: " + childId + "; But node with id " + childId + " does not exist in DB"));
                populateAllWords(child, allWords, currWord + ch);
            }
        }
        Long dashChildId = Node.getChildNodeId(root, '-');
        if (dashChildId != null) {
            Node child = wordsRepository.findById(dashChildId).orElseThrow(() -> new RuntimeException("Severe issue: Breakage in Trie in DB\nParent Node Id: " + root.getId() + "; Parent.childID: " + dashChildId + "; But node with id " + dashChildId + " does not exist in DB"));
            populateAllWords(child, allWords, currWord + "-");
        }
    }

    public void addAllWords(String text) {
        String[] words = text.split(" ");
        for (String word: words) {
            word = word.trim().toLowerCase();
            StringBuilder sanitizedWord = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if ((ch >= 'a' && ch <= 'z') || ch == '-') {
                    sanitizedWord.append(ch);
                }
            }
            addWord(sanitizedWord.toString());
        }
    }
}
