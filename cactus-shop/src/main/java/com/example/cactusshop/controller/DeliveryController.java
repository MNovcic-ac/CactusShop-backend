package com.example.cactusshop.controller;

import com.example.cactusshop.dto.delivery.CreateDeliveryDto;
import com.example.cactusshop.dto.delivery.DeliveryResponseDto;
import com.example.cactusshop.dto.delivery.UpdateDeliveryDto;
import com.example.cactusshop.mapper.DeliveryMapper;
import com.example.cactusshop.security.SecurityService;
import com.example.cactusshop.service.DeliveryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cactusshop.security.SecurityService.validateAdmin;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    @GetMapping
    public List<DeliveryResponseDto> getDeliveries(@RequestHeader("authorization") String token){
        validateAdmin(token);
        return deliveryMapper.mapToResponse(deliveryService.getDeliveries());
    }

    @GetMapping("/{uuid}")
    public DeliveryResponseDto getDelivery(@RequestHeader("authorization") String token, @PathVariable String uuid){
        validateAdmin(token);
        return deliveryMapper.mapToResponse(deliveryService.getById(uuid));
    }

    @PostMapping
    public DeliveryResponseDto create(@RequestHeader("authorization") String token, @RequestBody CreateDeliveryDto createDeliveryDto){
        validateAdmin(token);
        return deliveryMapper.mapToResponse(deliveryService.create(createDeliveryDto));
    }

    @PutMapping("/{uuid}")
    public DeliveryResponseDto update(@RequestHeader("authorization") String token, @PathVariable String uuid, @RequestBody UpdateDeliveryDto updateDeliveryDto){
        validateAdmin(token);
        return deliveryMapper.mapToResponse(deliveryService.update(updateDeliveryDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@RequestHeader("authorization") String token,@PathVariable String uuid){
        validateAdmin(token);
        deliveryService.delete(uuid);
    }
}
