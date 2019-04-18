package com.detelin.caseforce.web.controller;

import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.enums.ProductStatus;
import com.detelin.caseforce.domain.models.binding.ProductAddBindingModel;
import com.detelin.caseforce.domain.models.service.ProductServiceModel;
import com.detelin.caseforce.service.CloudService;
import com.detelin.caseforce.service.ProductService;
import com.detelin.caseforce.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
    private final ProductService productService;
    private final ModelMapper mapper;
    private final CloudService cloudService;
    @Autowired
    public ProductController(ProductService productService, ModelMapper mapper, CloudService cloudService) {
        this.productService = productService;
        this.mapper = mapper;
        this.cloudService = cloudService;
    }
    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    @PageTitle("Add Product")
    public ModelAndView addProduct(ModelAndView modelAndView){
        modelAndView.addObject("statuses", ProductStatus.values());
        return super.view("product/add-product",modelAndView);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public ModelAndView addProductConfirm(@ModelAttribute ProductAddBindingModel model,
                                          @RequestParam(name = "categories",required = false)List<String> subcategories
                                          ) throws IOException {
        ProductServiceModel productServiceModel = this.mapper.map(model, ProductServiceModel.class);
        productServiceModel.setImageUrl(
                this.cloudService.uploadImage(model.getImageUrl())
        );
        productServiceModel.getSubcategories().addAll(subcategories);
        this.productService.addProduct(productServiceModel);
        return super.redirect("/home");
    }
    @GetMapping("/show")
    @ResponseBody
    public List<ProductServiceModel> showProducts(){
        return this.productService.findAll();
    }
    @PostMapping("/categories")
    @ResponseBody
    public List<String> getCategoriesPerProduct(@RequestBody(required = true)  String product){
        List<String> categories = this.productService.categoriesPerProduct(product);
        int b = 5;
        return categories;
    }
}
