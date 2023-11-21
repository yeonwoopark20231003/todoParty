package com.example.todoparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude (JsonInclude.Include.NON_NULL)
public class CommonResponseDto {
    private String msg;
    private Integer statusCode;
}
