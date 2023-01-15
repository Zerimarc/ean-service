package com.prueba.springboot.eanservice.model.entity;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDb {
    private Integer id;
    private String name;
}
