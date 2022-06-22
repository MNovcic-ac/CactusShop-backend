package com.example.cactusshop.controller;

import com.example.cactusshop.dto.type.CreateProductTypeDto;
import com.example.cactusshop.dto.type.ProductTypeResponseDto;
import com.example.cactusshop.dto.type.UpdateProductTypeDto;
import com.example.cactusshop.mapper.ProductTypeMapper;
import com.example.cactusshop.service.ProductTypeService;
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
@RequestMapping("/api/categories")
public class ProductCategoryController {

    private final ProductTypeService productTypeService;
    private final ProductTypeMapper productTypeMapper;

    @GetMapping
    public List<ProductTypeResponseDto> getTypes(){
        return productTypeMapper.mapToResponse(productTypeService.getTypes());
    }

    @GetMapping("/{uuid}")
    public ProductTypeResponseDto getType(@PathVariable String uuid){
        return productTypeMapper.mapToResponse(productTypeService.getById(uuid));
    }

    @PostMapping
    public ProductTypeResponseDto create(@RequestBody CreateProductTypeDto createProductTypeDto){
        return productTypeMapper.mapToResponse(productTypeService.create(createProductTypeDto));
    }

    @PutMapping("/{uuid}")
    public ProductTypeResponseDto update(@PathVariable String uuid, @RequestBody UpdateProductTypeDto updateProductTypeDto){
        return productTypeMapper.mapToResponse(productTypeService.update(updateProductTypeDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        productTypeService.delete(uuid);
    }
}
