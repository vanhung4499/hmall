package com.hnv99.hmall.model.entity;

import com.hnv99.hmall.common.core.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private Integer status;
}
