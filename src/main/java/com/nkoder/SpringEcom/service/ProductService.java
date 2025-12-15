package com.nkoder.SpringEcom.service;

import com.nkoder.SpringEcom.model.Product;
import com.nkoder.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
      return  productRepo.findAll();

    }

    public Optional<Product> getById(int pId) {
       return productRepo.findById(pId);
    }

    public Optional<Product> getByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }
}
