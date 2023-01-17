package com.prueba.springboot.eanservice.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessRest {
    private Boolean success;
    private String message;
}
