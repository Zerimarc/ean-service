package com.prueba.springboot.eanservice.repository;

import com.prueba.springboot.eanservice.model.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}
