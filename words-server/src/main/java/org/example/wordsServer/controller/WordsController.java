package org.example.wordsServer.controller;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.example.wordsServer.dto.*;
import org.example.wordsServer.service.WordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/words")
@AllArgsConstructor
public class WordsController {
    private WordsService wordsService;

    @PostMapping
    public ResponseEntity<BasicApiResponse> addWord(@RequestBody AddWordRequestDTO addWordRequestDTO) {
        String word = addWordRequestDTO.getWord();
        if (word == null || word.isBlank()) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message("Blank word cannot be added")
                            .success(false)
                            .errorCode("ADD_EMPTY_WORD")
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            wordsService.addWord(word);
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message("Word " + word + " added successfully")
                            .success(true)
                            .build(),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message(e.getMessage())
                            .success(false)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @PostMapping("/bulkAdd")
    public ResponseEntity<BasicApiResponse> addAllWordsInText(@RequestBody AddAllWordsRequestDTO addAllWordsRequestDTO) {
        String text = addAllWordsRequestDTO.getText();
        if (text.isBlank()) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message("Blank text cannot be added")
                            .success(false)
                            .errorCode("ADD_EMPTY_TEXT")
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            wordsService.addAllWords(text);
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message("Words from text added successfully")
                            .success(true)
                            .build(),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message(e.getMessage())
                            .success(false)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @GetMapping("/{word}")
    public ResponseEntity<BasicApiResponse> getWordFrequency(@PathVariable String word) {
        if (word == null || word.isBlank()) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message("Blank word cannot be added")
                            .success(false)
                            .errorCode("GET_EMPTY_WORD_FREQUENCY")
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            Long f = wordsService.getWordFrequency(word);
            return new ResponseEntity<>(
                    GetWordFrequencyApiResponse
                            .builder()
                            .data(
                                    GetWordFrequencyApiResponse.Body
                                            .builder()
                                            .frequency(f)
                                            .word(word)
                                            .build()

                            )
                            .success(true)
                            .message("Fetched frequency for " + word)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message(e.getMessage())
                            .success(false)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @GetMapping
    public ResponseEntity<BasicApiResponse> getAllWords() {
        try {
            Pair<Long, Map<String, Long> > allWords = wordsService.getAllWordsWithTotalCount();
            return new ResponseEntity<>(
                    GetAllWordsApiResponse
                            .builder()
                            .data(
                                    GetAllWordsApiResponse.Body
                                            .builder()
                                            .allWords(allWords.getValue())
                                            .countWords(allWords.getKey())
                                            .build()

                            )
                            .success(true)
                            .message("Fetched all words")
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message(e.getMessage())
                            .success(false)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }

    @DeleteMapping("/{word}")
    public ResponseEntity<BasicApiResponse> deleteWord(@PathVariable String word) {
        if (word == null || word.isBlank()) {
            return new ResponseEntity<>(
                    ErrorApiResponse.builder()
                            .message("Blank word cannot be added")
                            .success(false)
                            .errorCode("ADD_EMPTY_WORD")
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            Boolean isDeleted = wordsService.deleteWord(word);
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message(Boolean.TRUE.equals(isDeleted) ? "Word " + word + " deleted successfully" : "Word " + word + " is not present")
                            .success(true)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    BasicApiResponse.builder()
                            .message(e.getMessage())
                            .success(false)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

        }
    }
}
