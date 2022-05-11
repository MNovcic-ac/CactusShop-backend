package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Supplier findSupplierBySupplierName(String name);
}
