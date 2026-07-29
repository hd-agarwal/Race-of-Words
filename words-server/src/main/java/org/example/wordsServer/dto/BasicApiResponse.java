package org.example.wordsServer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BasicApiResponse {
    private boolean success;
    private String message;
}
