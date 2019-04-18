package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    ProductServiceModel addProduct(ProductServiceModel productServiceModel);
    List<ProductServiceModel> findAll();
    List<String> findAllNames();
    List<String> categoriesPerProduct(String name);
}
