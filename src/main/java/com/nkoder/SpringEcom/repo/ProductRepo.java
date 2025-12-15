package com.nkoder.SpringEcom.repo;

import com.nkoder.SpringEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameContainingIgnoreCase(String name);
}
