package com.prueba.springboot.eanservice.model.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDb {

    @Id
    private Integer id;
    private String name;
}
