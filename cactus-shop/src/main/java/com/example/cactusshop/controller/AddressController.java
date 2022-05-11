package com.example.cactusshop.controller;

import com.example.cactusshop.dto.address.AddressResponseDto;
import com.example.cactusshop.dto.address.CreateAddressDto;
import com.example.cactusshop.dto.address.UpdateAddressDto;
import com.example.cactusshop.mapper.AddressMapper;
import com.example.cactusshop.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private  final AddressMapper addressMapper;

    @GetMapping
    public List<AddressResponseDto> getAddresses(){
        return addressMapper.mapToResponse(addressService.getAddresses());
    }

    @GetMapping("/{uuid}")
    public AddressResponseDto getAddress(@PathVariable String uuid){
        return addressMapper.mapToResponse(addressService.getAddressById(uuid));
    }

    @PostMapping
    public AddressResponseDto create(@RequestBody CreateAddressDto createAddressDto){
        return addressMapper.mapToResponse(addressService.create(createAddressDto));
    }

    @PutMapping("/{uuid}")
    public AddressResponseDto update(@RequestBody UpdateAddressDto updateAddressDto, @PathVariable String uuid){
        return addressMapper.mapToResponse(addressService.update(updateAddressDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        addressService.delete(uuid);
    }
}
