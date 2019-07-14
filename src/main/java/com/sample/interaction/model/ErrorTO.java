package com.sample.interaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorTO {
    private String message;
    private HttpStatus error;
    private String path;
    private int status;
    private String exception;


}