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
@Table(name = "roles")
public class Role extends CreatedBase{

    @Column(nullable = false)
    private String roleName;

    @Column
    private String roleDescription;
}
