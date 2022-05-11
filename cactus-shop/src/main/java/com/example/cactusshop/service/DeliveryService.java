package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.delivery.CreateDeliveryDto;
import com.example.cactusshop.dto.delivery.UpdateDeliveryDto;
import com.example.cactusshop.entity.Address;
import com.example.cactusshop.entity.Delivery;
import com.example.cactusshop.entity.DeliveryCompany;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.DeliveryMapper;
import com.example.cactusshop.repository.DeliveryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final AddressService addressService;
    private final DeliveryCompanyService deliveryCompanyService;

    public Delivery create(CreateDeliveryDto createDeliveryDto) {
        Delivery created = deliveryMapper.mapToEntity(createDeliveryDto);

        Address foundAddress = addressService.getAddressById(createDeliveryDto.getAddressId());
        DeliveryCompany foundCompany = deliveryCompanyService.getById(createDeliveryDto.getCompanyId());

        created.setDeliveryCompany(foundCompany);
        created.setAddress(foundAddress);

        return deliveryRepository.save(created);
    }

    public Delivery getById(String uuid) {
        return deliveryRepository.findById(uuid).orElseThrow(() -> {
            throw new NotFoundException(ErrorCodes.DELIVERY_NOT_FOUND);
        });
    }

    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery update(UpdateDeliveryDto updateDeliveryDto, String uuid) {
        Delivery foundDelivery = getById(uuid);

        Address foundAddress = addressService.getAddressById(updateDeliveryDto.getAddressId());
        DeliveryCompany foundCompany = deliveryCompanyService.getById(updateDeliveryDto.getCompanyId());

        foundDelivery.setDeliveryCompany(foundCompany);
        foundDelivery.setAddress(foundAddress);
        foundDelivery.setDeliveredAt(updateDeliveryDto.getDeliveredAt());
        foundDelivery.setCreatedAt(updateDeliveryDto.getCreatedAt());

        return deliveryRepository.save(foundDelivery);
    }

    public void delete(String uuid){
        Delivery foundDelivery = getById(uuid);

        deliveryRepository.delete(foundDelivery);
    }
}
