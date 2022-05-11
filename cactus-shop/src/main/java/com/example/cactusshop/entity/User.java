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
@Table(name = "users")
public class User extends CreatedBase{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String contactEmail;

    @Column
    private String contactPhone;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "fk_user_role")
    private Role role;
}
