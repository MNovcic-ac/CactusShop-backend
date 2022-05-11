package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.type.CreateProductTypeDto;
import com.example.cactusshop.dto.type.UpdateProductTypeDto;
import com.example.cactusshop.entity.ProductType;
import com.example.cactusshop.exception.ConflictException;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.ProductTypeMapper;
import com.example.cactusshop.repository.ProductTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public ProductType create(CreateProductTypeDto createProductTypeDto){
        if(productTypeRepository.findByTypeName(createProductTypeDto.getTypeName()) != null){
            throw new ConflictException(ErrorCodes.PRODUCT_TYPE_FOUND);
        }

        ProductType created = productTypeMapper.mapToEntity(createProductTypeDto);

        return productTypeRepository.save(created);
    }

    public ProductType getById(String uuid){
        return productTypeRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(ErrorCodes.PRODUCT_TYPE_NOT_FOUND);
        });
    }

    public ProductType getById(String uuid, String errorCode){
        return productTypeRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(errorCode);
        });
    }

    public List<ProductType> getTypes(){
        return productTypeRepository.findAll();
    }

    public ProductType update(UpdateProductTypeDto updateProductTypeDto, String uuid){
        if(productTypeRepository.findByTypeName(updateProductTypeDto.getTypeName()) != null){
            throw new ConflictException(ErrorCodes.PRODUCT_TYPE_FOUND);
        }

        if(productTypeRepository.findById(uuid).isEmpty()){
            throw new NotFoundException(ErrorCodes.PRODUCT_TYPE_NOT_FOUND_2);
        }

        ProductType foundType = productTypeRepository.findById(uuid).get();

        foundType.setTypeName(updateProductTypeDto.getTypeName());

        return productTypeRepository.save(foundType);
    }

    public void delete(String uuid){
        if(productTypeRepository.findById(uuid).isEmpty()){
            throw new NotFoundException(ErrorCodes.PRODUCT_TYPE_NOT_FOUND_3);
        }

        ProductType foundType = productTypeRepository.findById(uuid).get();

        productTypeRepository.delete(foundType);
    }
}
