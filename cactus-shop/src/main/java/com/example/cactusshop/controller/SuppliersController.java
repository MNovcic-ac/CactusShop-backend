package com.example.cactusshop.controller;

import com.example.cactusshop.dto.supplier.CreateSupplierDto;
import com.example.cactusshop.dto.supplier.SupplierResponseDto;
import com.example.cactusshop.dto.supplier.UpdateSupplierDto;
import com.example.cactusshop.mapper.SupplierMapper;
import com.example.cactusshop.service.SupplierService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suppliers")
public class SuppliersController {

    private final SupplierService supplierService;
    private final SupplierMapper supplierMapper;

    @GetMapping
    public List<SupplierResponseDto> getSuppliers(){
        return supplierMapper.mapToResponse(supplierService.getSuppliers());
    }

    @GetMapping("/{uuid}")
    public SupplierResponseDto getSupplier(@PathVariable String uuid){
        return supplierMapper.mapToResponse(supplierService.getById(uuid));
    }

    @PostMapping
    public SupplierResponseDto create(@RequestBody CreateSupplierDto createSupplierDto){
        return supplierMapper.mapToResponse(supplierService.create(createSupplierDto));
    }

    @PutMapping("/{uuid}")
    public SupplierResponseDto update(@PathVariable String uuid, @RequestBody UpdateSupplierDto updateSupplierDto){
        return supplierMapper.mapToResponse(supplierService.update(updateSupplierDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        supplierService.delete(uuid);
    }
}
