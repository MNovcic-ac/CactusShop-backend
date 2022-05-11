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
@Table(name = "addresses")
public class Address extends CreatedBase{

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String town;

    @Column(nullable = false)
    private String postalCode;
}
