package com.example.cactusshop.entity;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "deliveries")
public class Delivery extends CreatedBase{

    @Column(nullable = false)
    private OffsetDateTime deliveredAt;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_delivery_address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "fk_delivery_comapny")
    private DeliveryCompany deliveryCompany;
}
