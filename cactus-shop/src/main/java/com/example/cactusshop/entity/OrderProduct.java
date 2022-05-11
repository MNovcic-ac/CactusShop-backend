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
@Table(name = "orders_products")
public class OrderProduct extends CreatedBase{

    @ManyToOne
    @JoinColumn(name = "fk_order_product_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_order_product_product")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
}
