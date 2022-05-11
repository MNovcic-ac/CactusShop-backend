package com.example.cactusshop.service;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.dto.company.CreateCompanyDto;
import com.example.cactusshop.dto.company.UpdateCompanyDto;
import com.example.cactusshop.entity.DeliveryCompany;
import com.example.cactusshop.exception.NotFoundException;
import com.example.cactusshop.mapper.DeliveryCompanyMapper;
import com.example.cactusshop.repository.DeliveryCompanyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryCompanyService {

    private final DeliveryCompanyRepository deliveryCompanyRepository;
    private final DeliveryCompanyMapper deliveryCompanyMapper;

    public DeliveryCompany create(CreateCompanyDto createCompanyDto){
        DeliveryCompany company = deliveryCompanyMapper.mapToEntity(createCompanyDto);

        return deliveryCompanyRepository.save(company);
    }

    public DeliveryCompany getById(String uuid){
        return deliveryCompanyRepository.findById(uuid).orElseThrow(()->{
           throw new NotFoundException(ErrorCodes.COMPANY_NOT_FOUND);
        });
    }

    public DeliveryCompany getById(String uuid, String errorCode){
        return deliveryCompanyRepository.findById(uuid).orElseThrow(()->{
            throw new NotFoundException(errorCode);
        });
    }

    public List<DeliveryCompany> getCompanies(){
        return deliveryCompanyRepository.findAll();
    }

    public DeliveryCompany update(UpdateCompanyDto updateCompanyDto, String uuid){
        DeliveryCompany foundCompany = getById(uuid);

        foundCompany.setCompanyContact(updateCompanyDto.getCompanyContact());
        foundCompany.setCompanyName(updateCompanyDto.getCompanyName());

        return deliveryCompanyRepository.save(foundCompany);
    }

    public void delete (String uuid){
        deliveryCompanyRepository.delete(getById(uuid));
    }
}
