package com.example.cactusshop.repository;

import com.example.cactusshop.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, String> {
    ProductType findByTypeName(String name);
}
