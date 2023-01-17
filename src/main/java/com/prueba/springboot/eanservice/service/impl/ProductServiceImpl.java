package com.prueba.springboot.eanservice.service.impl;

import com.prueba.springboot.eanservice.dto.ProductDTO;
import com.prueba.springboot.eanservice.model.entity.Product;
import com.prueba.springboot.eanservice.model.entity.Supplier;
import com.prueba.springboot.eanservice.model.rest.ProductRest;
import com.prueba.springboot.eanservice.model.rest.SuccessRest;
import com.prueba.springboot.eanservice.repository.ProductRepository;
import com.prueba.springboot.eanservice.repository.SupplierRepository;
import com.prueba.springboot.eanservice.service.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ResponseEntity<Object> addProduct(Product product, BindingResult bindingResult){
        try{
            product = productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch(ConstraintViolationException e){
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<SuccessRest> delProduct(Integer id){
        try{
            Product product = productRepository.findById(id).get();
            productRepository.delete(product);
            return new ResponseEntity<>(new SuccessRest(true, "Producto eliminado"), HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new SuccessRest(false, "Producto no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> editProduct(Product product, BindingResult bindingResult) {
        try{
            productRepository.save(product);
            return new ResponseEntity<>(new SuccessRest(true, "Producto modificado"), HttpStatus.OK);
        } catch(ConstraintViolationException e){
            return new ResponseEntity<>(new SuccessRest(false, bindingResult.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getProductById(Integer id){
        try{
            Product product = productRepository.findById(id).get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return new ResponseEntity<>(new SuccessRest(false, "Producto no encontrado"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getProductInfoByEAN(String ean){
        if (ean.length() != 13) {
            SuccessRest msgError = new SuccessRest(false, "El código EAN debe tener 13 digitos");
            return new ResponseEntity<>(msgError, HttpStatus.LENGTH_REQUIRED);
        }
        Integer idSupplier = Integer.valueOf(ean.substring(0, 7));
        Integer idProduct = Integer.valueOf(ean.substring(7, 12));
        Integer destination = Integer.valueOf(ean.substring(12));

        try{
            Product product = productRepository.findById(idProduct).get();
            Supplier supplier = supplierRepository.findById(idSupplier).get();

            ProductRest productRest = ProductDTO.productToProductRest(product, supplier);

            this.setDestination(productRest, destination);

            if (productRest.getDestination() == null || productRest.getDestination().isEmpty()) {
                return new ResponseEntity<>(new SuccessRest(false, "Código de destino incorrecto"), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(productRest, HttpStatus.OK);

        } catch(NoSuchElementException e) {
            return new ResponseEntity<>(new SuccessRest(false, "Código EAN incorrecto"), HttpStatus.NOT_FOUND);
        }
    }

    private void setDestination(ProductRest productRest, Integer destination){
        if (destination >= 1 && destination <=5) {
            productRest.setDestination("Mercadona España");
        }

        if (destination == 6) {
            productRest.setDestination("Mercadona Portugal");
        }

        if (destination == 8) {
            productRest.setDestination("Almacenes");
        }

        if (destination == 9) {
            productRest.setDestination("Oficinas Mercadona");
        }

        if (destination == 0) {
            productRest.setDestination("Colmenas");
        }
    }

}
