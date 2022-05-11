package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
}
