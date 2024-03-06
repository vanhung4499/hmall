package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.Product;
import com.hnv99.hmall.model.form.ProductForm;
import com.hnv99.hmall.model.query.ProductPageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductService {
    boolean createProduct(ProductForm formData);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    boolean updateProduct(Long id, ProductForm formData);
    boolean deleteProduct(Long id);
    Page<Product> getAllProducts(ProductPageQuery queryParams);
}