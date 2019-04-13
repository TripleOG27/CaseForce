package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.Subcategory;
import com.detelin.caseforce.domain.models.service.ProductServiceModel;
import com.detelin.caseforce.repository.ProductRepository;
import com.detelin.caseforce.repository.SubcategoryRepository;
import org.dom4j.IllegalAddException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, SubcategoryRepository subcategoryRepository, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        Product product = this.productRepository
                .findByName(productServiceModel.getName())
                .orElse(null);

        if (product != null) {
            throw new IllegalAddException("Product already exists");
        }
        product = this.mapper.map(productServiceModel, Product.class);

        List<Subcategory> subcategoriesList= productServiceModel.getSubcategories().stream().map(c->this.mapper.map(c, Subcategory.class)).collect(Collectors.toList());
        product.getSubcategories().addAll(subcategoriesList);
        product = this.productRepository.saveAndFlush(product);

        return this.mapper.map(product, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAll() {
        return  this.productRepository.findAll().stream().map(p->this.mapper.map(p,ProductServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllNames() {
        return  this.productRepository.findAll().stream().map(p->p.getName()).collect(Collectors.toList());
    }
}
