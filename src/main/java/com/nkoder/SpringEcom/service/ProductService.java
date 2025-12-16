package com.nkoder.SpringEcom.service;

import com.nkoder.SpringEcom.model.Product;
import com.nkoder.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public Product addOrUpdateProduct(Product product, MultipartFile image)
            throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return productRepo.save(product);
    }



    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }


    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }

}
