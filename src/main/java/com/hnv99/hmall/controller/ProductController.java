package com.hnv99.hmall.controller;

import com.hnv99.hmall.common.core.result.PageResult;
import com.hnv99.hmall.common.core.result.Result;
import com.hnv99.hmall.model.entity.Product;
import com.hnv99.hmall.model.form.ProductForm;
import com.hnv99.hmall.model.query.ProductPageQuery;
import com.hnv99.hmall.serivce.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product Management")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @Operation(summary = "Create a new product")
    @PostMapping
    public Result createProduct(@RequestBody ProductForm formData) {
        boolean result = productService.createProduct(formData);
        return Result.judge(result);
    }

    @Operation(summary = "Get a product by its ID")
    @GetMapping("/{id}")
    public Result getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return Result.success(product);
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public Result getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return Result.success(products);
    }

    @Operation(summary = "Update a product by its ID")
    @PatchMapping("/{id}")
    public Result updateProduct(@PathVariable Long id, @RequestBody ProductForm formData) {
        boolean result = productService.updateProduct(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "Delete a product by its ID")
    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Long id) {
        boolean result = productService.deleteProduct(id);
        return Result.judge(result);
    }

    @Operation(summary = "Get all products with pagination")
    @GetMapping("/page")
    public PageResult getAllProducts(ProductPageQuery queryParams) {
        Page<Product> products = productService.getAllProducts(queryParams);
        return PageResult.success(products);
    }
}