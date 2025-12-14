package com.nkoder.SpringEcom.service;

import com.nkoder.SpringEcom.model.Product;
import com.nkoder.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
      return  productRepo.findAll();

    }
}
