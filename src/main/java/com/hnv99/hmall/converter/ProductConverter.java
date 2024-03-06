package com.hnv99.hmall.converter;

import com.hnv99.hmall.model.entity.Product;
import com.hnv99.hmall.model.form.ProductForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductConverter {
    Product form2Entity(ProductForm form);
    ProductForm entity2Form(Product entity);
}
