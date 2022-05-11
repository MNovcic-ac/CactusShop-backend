package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductName(String name);
}
