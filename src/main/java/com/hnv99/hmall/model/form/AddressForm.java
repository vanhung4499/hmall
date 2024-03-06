package com.hnv99.hmall.model.form;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressForm {
    private Long userId;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private Boolean isDefault;
}
