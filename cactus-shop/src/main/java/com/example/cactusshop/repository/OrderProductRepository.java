package com.example.cactusshop.repository;

import com.example.cactusshop.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, String> {
}
