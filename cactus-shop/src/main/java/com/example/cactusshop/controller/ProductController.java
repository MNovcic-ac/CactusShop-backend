package com.example.cactusshop.controller;

import com.example.cactusshop.dto.product.CreateProductDto;
import com.example.cactusshop.dto.product.ProductResponseDto;
import com.example.cactusshop.dto.product.UpdateProductDto;
import com.example.cactusshop.mapper.ProductMapper;
import com.example.cactusshop.service.ProductService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponseDto> getProducts(
            @RequestParam(name = "supplier", required = false) List<String> suppliers,
            @RequestParam(name = "page", required = false, defaultValue = "1")Integer page){
        return productMapper.mapToResponse(productService.getProducts(suppliers, page));
    }

    @GetMapping("/{uuid}")
    public ProductResponseDto getProduct(@PathVariable String uuid){
        return productMapper.mapToResponse(productService.getById(uuid));
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody CreateProductDto createProductDto){
        return  productMapper.mapToResponse(productService.create(createProductDto));
    }

    @PutMapping("/{uuid}")
    public ProductResponseDto update(@PathVariable String uuid, @RequestBody UpdateProductDto updateProductDto){
        return productMapper.mapToResponse(productService.update(updateProductDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        productService.delete(uuid);
    }

    @PostMapping("/{uuid}/{amount}")
    public ProductResponseDto addStocks(@PathVariable String uuid, @PathVariable Integer amount){
        return productMapper.mapToResponse(productService.addStocks(uuid, amount));
    }
}
