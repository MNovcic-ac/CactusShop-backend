package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.product.CreateProductDto;
import com.example.cactusshop.dto.product.UpdateProductDto;
import com.example.cactusshop.entity.Product;
import com.example.cactusshop.entity.ProductType;
import com.example.cactusshop.entity.Supplier;
import com.example.cactusshop.exception.ConflictException;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.ProductMapper;
import com.example.cactusshop.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductTypeService productTypeService;
    private final SupplierService supplierService;

    public Product create(CreateProductDto createProductDto){
        if(productRepository.findByProductName(createProductDto.getProductName())!=null){
            throw new ConflictException(ErrorCodes.PRODUCT_FOUND);
        }

        Supplier foundSupplier = supplierService.getById(createProductDto.getSupplierId());
        ProductType foundType = productTypeService.getById(createProductDto.getProductTypeId());

        Product created = productMapper.mapToEntity(createProductDto);
        created.setSupplier(foundSupplier);
        created.setType(foundType);

        return productRepository.save(created);
    }

    public Product getById(String uuid){
        return productRepository.findById(uuid).orElseThrow(()->{
           throw new NotFoundException(ErrorCodes.PRODUCT_NOT_FOUND);
        });
    }

    public Product getById(String uuid, String errorCode){
        return productRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(errorCode);
        });
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product update(UpdateProductDto updateProductDto, String uuid){
        Product foundProduct = getById(uuid, ErrorCodes.PRODUCT_NOT_FOUND_2);

        if(productRepository.findByProductName(updateProductDto.getProductName())!=null){
            throw new ConflictException(ErrorCodes.PRODUCT_FOUND);
        }

        Supplier foundSupplier = supplierService.getById(updateProductDto.getSupplierId());
        ProductType foundType = productTypeService.getById(updateProductDto.getProductTypeId());

        foundProduct = buildUpdatedProduct(foundProduct, updateProductDto, foundSupplier, foundType);

        return productRepository.save(foundProduct);
    }

    private Product buildUpdatedProduct(Product product, UpdateProductDto updateProductDto, Supplier supplier, ProductType type){
        product.setProductName(updateProductDto.getProductName());
        product.setProductDescription(updateProductDto.getProductDescription());
        product.setStock(updateProductDto.getStock());
        product.setType(type);
        product.setSupplier(supplier);
        product.setLifeExpectancy(updateProductDto.getLifeExpectancy());
        product.setPrice(updateProductDto.getPrice());

        return product;
    }

    public void delete(String uuid){
        Product foundProduct = getById(uuid, ErrorCodes.PRODUCT_NOT_FOUND_3);

        productRepository.delete(foundProduct);
    }

    public Product addStocks(String productId, Integer toAdd){
        Product foundProduct = getById(productId);
        foundProduct.setStock(foundProduct.getStock() + toAdd);

        return foundProduct;
    }

}
