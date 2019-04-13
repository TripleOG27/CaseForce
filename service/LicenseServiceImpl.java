package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.entities.License;
import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.PurchasedLicense;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.models.service.LicenseServiceModel;
import com.detelin.caseforce.domain.models.view.PurchasedLicenseViewModel;
import com.detelin.caseforce.repository.LicenseRepository;
import com.detelin.caseforce.repository.ProductRepository;
import com.detelin.caseforce.repository.PurchasedLicenseRepository;
import com.detelin.caseforce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseServiceImpl implements LicenseService {
    private final ModelMapper mapper;
    private final LicenseRepository licenseRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PurchasedLicenseRepository purchasedLicenseRepository;


    public LicenseServiceImpl(ModelMapper mapper, LicenseRepository licenseRepository, ProductRepository productRepository, ProductRepository productRepository1, UserService userService, UserRepository userRepository, PurchasedLicenseRepository purchasedLicenseRepository) {
        this.mapper = mapper;
        this.licenseRepository = licenseRepository;

        this.productRepository = productRepository1;
        this.userService = userService;
        this.userRepository = userRepository;
        this.purchasedLicenseRepository = purchasedLicenseRepository;

    }



    @Override
    public LicenseServiceModel addLicense(LicenseServiceModel licenseServiceModel) {
        License license = this.mapper.map(licenseServiceModel,License.class);
        this.licenseRepository.saveAndFlush(license);
        return this.mapper.map(license,LicenseServiceModel.class);
    }

    @Override
    public List<LicenseServiceModel> findAllLicenses() {
        List<License>licenseList = this.licenseRepository.findAll();
        List<License> purchasedLicenses  = this.purchasedLicenseRepository.findAllByPurchasedDateIsNull();
        return this.licenseRepository.findAll().stream().filter(l->l.getClass().getSimpleName().equals("License")).map(l-> this.mapper.map(l,LicenseServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void purchaseLicense(String license, List<String> products, String client) {
        PurchasedLicense license2 = mapper.map(this.findByName(license),PurchasedLicense.class);
        List<Product> productList = products.stream().map(p->this.productRepository.findByName(p).orElseThrow()).collect(Collectors.toList());
        User client1 = this.mapper.map(this.userService.findUserByUsername(client),User.class);

        license2.getProduct().addAll(productList);
        license2.setPurchasedDate(LocalDate.now());
        license2.getClients().add(client1);

        this.purchasedLicenseRepository.saveAndFlush(license2);

    }

    private License findByName(String name) {
        List<License> licenses = this.licenseRepository.findAll().stream().filter(l->l.getClass().getSimpleName().equals("License")).collect(Collectors.toList());
        License license= licenses.stream().filter(l->l.getName().equals(name)).collect(Collectors.toList()).get(0);
        return license;
    }

    @Override
    public List<PurchasedLicenseViewModel> showLicenses(String username) {
        User client = this.mapper.map(this.userService.findUserByUsername(username),User.class);
        List<PurchasedLicense> purchasedLicenseList= this.purchasedLicenseRepository.findAllByClientsContains(client);
        return purchasedLicenseList.stream()
                .map(p-> this.mapper.map(p,PurchasedLicenseViewModel.class)).collect(Collectors.toList());
    }
}
