package com.example.cactusshop.controller;

import com.example.cactusshop.dto.company.CompanyResponseDto;
import com.example.cactusshop.dto.company.CreateCompanyDto;
import com.example.cactusshop.dto.company.UpdateCompanyDto;
import com.example.cactusshop.mapper.DeliveryCompanyMapper;
import com.example.cactusshop.service.DeliveryCompanyService;
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
@RequestMapping("/api/companies")
public class DeliveryCompanyController {

    private final DeliveryCompanyService deliveryCompanyService;
    private final DeliveryCompanyMapper deliveryCompanyMapper;

    @GetMapping
    public List<CompanyResponseDto> getCompanies(){
        return deliveryCompanyMapper.mapToResponse(deliveryCompanyService.getCompanies());
    }

    @GetMapping("/{uuid}")
    public CompanyResponseDto getCompany(@PathVariable String uuid){
        return deliveryCompanyMapper.mapToResponse(deliveryCompanyService.getById(uuid));
    }

    @PostMapping
    public CompanyResponseDto create(@RequestBody CreateCompanyDto createCompanyDto){
        return deliveryCompanyMapper.mapToResponse(deliveryCompanyService.create(createCompanyDto));
    }

    @PutMapping("/{uuid}")
    public CompanyResponseDto update(@PathVariable String uuid, @RequestBody UpdateCompanyDto updateCompanyDto){
        return deliveryCompanyMapper.mapToResponse(deliveryCompanyService.update(updateCompanyDto, uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable String uuid){
        deliveryCompanyService.delete(uuid);
    }
}
