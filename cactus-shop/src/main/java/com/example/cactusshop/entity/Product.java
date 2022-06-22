package com.example.cactusshop.entity;

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
@Table(name = "products")
public class Product extends CreatedBase{

    @Column(nullable = false)
    private String productName;

    @Column(nullable = true)
    private String productDescription;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = true)
    private Long lifeExpectancy;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "fk_product_supplier")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "fk_product_product_type")
    private ProductType type;

    @Column
    private String image;
}
