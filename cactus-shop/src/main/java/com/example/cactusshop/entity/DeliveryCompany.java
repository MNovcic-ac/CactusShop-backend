package com.example.cactusshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "deliver_companies")
public class DeliveryCompany extends CreatedBase{

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String companyContact;
}
