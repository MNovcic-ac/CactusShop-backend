package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
