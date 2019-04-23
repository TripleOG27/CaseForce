package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.Subcategory;
import com.detelin.caseforce.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    ProductServiceModel addProduct(ProductServiceModel productServiceModel);
    List<ProductServiceModel> findAll();
    List<String> findAllNames();
    List<String> categoriesPerProduct(String name);
    Product findProductByName(String name);
    Subcategory findSubcategoryOfProduct(String product, String subcategory);
}
