package com.detelin.caseforce.web.controller;

import com.detelin.caseforce.domain.models.binding.LicenseAddBindingModel;
import com.detelin.caseforce.domain.models.binding.LicensePurchaseBindingModel;
import com.detelin.caseforce.domain.models.service.LicenseServiceModel;
import com.detelin.caseforce.service.LicenseService;
import com.detelin.caseforce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/licenses")
public class LicenseController extends BaseController {
    private final ModelMapper mapper;
    private final LicenseService licenseService;
    private final ProductService productService;

    @Autowired
    public LicenseController(ModelMapper mapper, LicenseService licenseService, ProductService productService) {
        this.mapper = mapper;
        this.licenseService = licenseService;
        this.productService = productService;
    }
    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ModelAndView addLicense(ModelAndView modelAndView){
        return super.view("license/add-license",modelAndView);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ModelAndView addLicenseConfirm(@ModelAttribute LicenseAddBindingModel licenseAddBindingModel){
        LicenseServiceModel licenseServiceModel = this.mapper.map(licenseAddBindingModel,LicenseServiceModel.class);
        this.licenseService.addLicense(licenseServiceModel);
        return super.redirect("all");

    }
    @GetMapping("/all")
    public ModelAndView allLicenses(ModelAndView modelAndView){
        modelAndView.addObject("licenses",this.licenseService.findAllLicenses());
        return super.view("license/all-licenses",modelAndView);
    }
    @GetMapping("/purchase")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView purchaseLicense(ModelAndView modelAndView){
        modelAndView.addObject("licenses",this.licenseService.findAllLicenses().stream().map(LicenseServiceModel::getName).collect(Collectors.toList()));
        modelAndView.addObject("products",this.productService.findAllNames());
        return super.view("license/purchase-license",modelAndView);
    }
    @PostMapping("/purchase")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView purchaseLicenseConfirm(Principal principal, @ModelAttribute LicensePurchaseBindingModel purchaseBindingModel){
        this.licenseService.purchaseLicense(purchaseBindingModel.getLicense(),purchaseBindingModel.getProducts(),principal.getName());
        return super.redirect("my");
    }
    @GetMapping("/my")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView showLicenses(ModelAndView modelAndView,Principal principal){
        modelAndView.addObject("licenses",this.licenseService.showLicenses(principal.getName()));
        return super.view("license/my-licenses",modelAndView);
    }
}
