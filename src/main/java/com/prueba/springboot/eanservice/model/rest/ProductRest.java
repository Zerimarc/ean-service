package com.prueba.springboot.eanservice.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRest {

    private Integer id;
    private String name;
    private Integer price;
    private String supplier;
    private String destination;
}
