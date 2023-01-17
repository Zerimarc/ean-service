package com.prueba.springboot.eanservice.repository;

import com.prueba.springboot.eanservice.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
