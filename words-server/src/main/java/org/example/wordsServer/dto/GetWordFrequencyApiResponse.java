package org.example.wordsServer.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GetWordFrequencyApiResponse extends BasicApiResponse{
    @Builder
    @Data
    public static class Body {
        Long frequency;
        String word;
    }
    private Body data;
}
