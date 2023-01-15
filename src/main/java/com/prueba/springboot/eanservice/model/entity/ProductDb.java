package com.prueba.springboot.eanservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDb {

    @Id
    private Integer id;
    private String name;
    private BigDecimal price;
}
