package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.address.CreateAddressDto;
import com.example.cactusshop.dto.address.UpdateAddressDto;
import com.example.cactusshop.entity.Address;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.AddressMapper;
import com.example.cactusshop.repository.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public Address create(CreateAddressDto createAddressDto){
        Address created = addressMapper.mapToEntity(createAddressDto);
        return addressRepository.save(created);
    }

    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    public Address getAddressById(String uuid){
        return addressRepository.findById(uuid).orElseThrow(()->{
            log.info("Address with id '{}' does not exist", uuid);
            throw new NotFoundException(ErrorCodes.ADDRESS_NOT_FOUND);
        });
    }

    public Address getAddressById(String uuid, String errorCode){
        return addressRepository.findById(uuid).orElseThrow(()->{
            log.info("Address with id '{}' does not exist", uuid);
            throw new NotFoundException(errorCode);
        });
    }

    public Address update(UpdateAddressDto updateAddressDto, String uuid){
        Address foundAddress = getAddressById(uuid, ErrorCodes.ADDRESS_NOT_FOUND_2);
        foundAddress = buildUpdatedAddress(foundAddress, updateAddressDto);

        return addressRepository.save(foundAddress);
    }

    private Address buildUpdatedAddress(Address foundAddress, UpdateAddressDto updateAddressDto) {
        foundAddress.setStreetName(updateAddressDto.getStreetName());
        foundAddress.setStreetNumber(updateAddressDto.getStreetNumber());
        foundAddress.setTown(updateAddressDto.getTown());
        foundAddress.setPostalCode(updateAddressDto.getPostalCode());

        return foundAddress;
    }

    public void delete(String uuid){
        Address foundAddress = getAddressById(uuid, ErrorCodes.ADDRESS_NOT_FOUND_3);

        addressRepository.delete(foundAddress);
    }
}
