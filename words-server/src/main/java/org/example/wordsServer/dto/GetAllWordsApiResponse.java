package org.example.wordsServer.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GetAllWordsApiResponse extends BasicApiResponse{
    @Builder
    @Data
    public static class Body {
        Map<String, Long> allWords;
        Long countWords;
    }
    private Body data;
}
