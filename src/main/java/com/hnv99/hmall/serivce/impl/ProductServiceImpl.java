package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.converter.ProductConverter;
import com.hnv99.hmall.repository.ProductRepository;
import com.hnv99.hmall.model.entity.Product;
import com.hnv99.hmall.model.form.ProductForm;
import com.hnv99.hmall.model.query.ProductPageQuery;
import com.hnv99.hmall.serivce.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;


    @Override
    public boolean createProduct(ProductForm formData) {
        Product product = productConverter.form2Entity(formData);
        Product result = productRepository.save(product);
        return true;
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean updateProduct(Long id, ProductForm formData) {
        Product product = productRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(formData, product);
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public Page<Product> getAllProducts(ProductPageQuery queryParams) {
        Pageable pageable = PageRequest.of(queryParams.getPageNum(), queryParams.getPageSize());
        return productRepository.findAll(pageable);
    }
}