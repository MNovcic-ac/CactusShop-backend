package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.supplier.CreateSupplierDto;
import com.example.cactusshop.dto.supplier.UpdateSupplierDto;
import com.example.cactusshop.entity.Address;
import com.example.cactusshop.entity.Supplier;
import com.example.cactusshop.exception.ConflictException;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.SupplierMapper;
import com.example.cactusshop.repository.SupplierRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final AddressService addressService;

    public Supplier create(CreateSupplierDto createSupplierDto){
        if(supplierRepository.findSupplierBySupplierName(createSupplierDto.getSupplierName()) != null){
            throw new ConflictException(ErrorCodes.SUPPLIER_FOUND);
        }

        Address foundAddress = addressService.getAddressById(createSupplierDto.getAddressId());

        Supplier created = supplierMapper.mapToEntity(createSupplierDto);
        created.setAddress(foundAddress);

        return supplierRepository.save(created);
    }

    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier getById(String uuid){
        return supplierRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(ErrorCodes.SUPPLIER_NOT_FOUND_1);
        });
    }

    private Supplier getById(String uuid, String errorCode){
        return supplierRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(errorCode);
        });
    }

    public Supplier update(UpdateSupplierDto updateSupplierDto, String uuid){
        if(supplierRepository.findSupplierBySupplierName(updateSupplierDto.getSupplierName()) != null){
            throw new ConflictException(ErrorCodes.SUPPLIER_FOUND);
        }

        Address foundAddress = addressService.getAddressById(updateSupplierDto.getAddressId());

        if(supplierRepository.findById(uuid).isEmpty()){
            throw new NotFoundException(ErrorCodes.SUPPLIER_NOT_FOUND_2);
        }

        Supplier foundSupplier = supplierRepository.findById(uuid).get();

        foundSupplier.setSupplierName(updateSupplierDto.getSupplierName());
        foundSupplier.setSupplierContactEmail(updateSupplierDto.getSupplierContactEmail());
        foundSupplier.setSupplierContactPhone(updateSupplierDto.getSupplierContactPhone());
        foundSupplier.setAddress(foundAddress);

        return supplierRepository.save(foundSupplier);
    }

    public void delete(String uuid){
        if(supplierRepository.findById(uuid).isEmpty()){
            throw new NotFoundException(ErrorCodes.SUPPLIER_NOT_FOUND_3);
        }

        Supplier foundSupplier = supplierRepository.findById(uuid).get();

        supplierRepository.delete(foundSupplier);
    }
}
