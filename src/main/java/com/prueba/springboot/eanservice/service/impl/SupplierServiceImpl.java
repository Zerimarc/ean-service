package com.prueba.springboot.eanservice.service.impl;

import com.prueba.springboot.eanservice.model.entity.Supplier;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import com.prueba.springboot.eanservice.repository.SupplierRepository;
import com.prueba.springboot.eanservice.service.SupplierService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ResponseEntity<Object> addSupplier(Supplier supplier, BindingResult bindingResult) {
        try {
            supplier = supplierRepository.save(supplier);
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch(ConstraintViolationException e) {
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<SuccessRest> delSupplier(Integer id) {
        try {
            Supplier supplier = supplierRepository.findById(id).get();
            supplierRepository.delete(supplier);
            return new ResponseEntity<>(new SuccessRest(true, "Proveedor eliminado"), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new SuccessRest(false, "Proveedor no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> editSupplier(Supplier supplier, BindingResult bindingResult) {
        try {
            supplierRepository.save(supplier);
            return new ResponseEntity<>(new SuccessRest(true, "Proveedor modificado"), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getSupplierById(Integer id) {
        try {
            Supplier supplier = supplierRepository.findById(id).get();
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new SuccessRest(false, "Proveedor no encontrado"), HttpStatus.NOT_FOUND);
        }
    }
}
