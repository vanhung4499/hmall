package com.hnv99.hmall.model.form;

import lombok.Data;

@Data
public class ProductForm {
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
