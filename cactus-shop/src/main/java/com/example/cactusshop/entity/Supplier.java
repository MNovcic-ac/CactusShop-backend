package com.example.cactusshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;

@Entity
@Getter
@Setter
@ToString
@Table(name = "suppliers")
@Check(constraints = "(supplier_Contact_Email IS NULL AND supplier_Contact_Phone IS NOT NULL) " +
        "OR (supplier_Contact_Email IS NOT NULL AND supplier_Contact_Phone IS NULL) " +
        "OR (supplier_Contact_Email IS NOT NULL AND supplier_Contact_Phone IS NOT NULL)")
public class Supplier extends CreatedBase{

    @Column(nullable = false)
    private String supplierName;

    @Column
    private String supplierContactEmail;

    @Column
    private String supplierContactPhone;

    @OneToOne
    @JoinColumn(name = "fk_supplier_address")
    private Address address;
}
