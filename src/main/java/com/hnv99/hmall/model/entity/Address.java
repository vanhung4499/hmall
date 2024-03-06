package com.hnv99.hmall.model.entity;

import com.hnv99.hmall.common.core.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "addresses")
public class Address extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "is_default")
    private Boolean isDefault;
}