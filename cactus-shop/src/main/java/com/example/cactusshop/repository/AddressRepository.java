package com.example.cactusshop.repository;

import com.example.cactusshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
