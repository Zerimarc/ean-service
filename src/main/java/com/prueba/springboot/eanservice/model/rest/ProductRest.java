package com.prueba.springboot.eanservice.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRest {

    private Integer id;
    private String name;
    private BigDecimal price;
    private String supplier;
    private String destination;
}
