package org.example.wordsServer.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class ErrorApiResponse extends BasicApiResponse{
    private String errorCode;
}
