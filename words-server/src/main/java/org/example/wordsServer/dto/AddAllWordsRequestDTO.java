package org.example.wordsServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class AddAllWordsRequestDTO {
    private String text;
}
