package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.service.LicenseServiceModel;
import com.detelin.caseforce.domain.models.view.PurchasedLicenseViewModel;

import java.util.List;

public interface LicenseService {
    LicenseServiceModel addLicense(LicenseServiceModel licenseServiceModel);
    List<LicenseServiceModel> findAllLicenses();
    void purchaseLicense(String license, List<String> products, String client);
    List<PurchasedLicenseViewModel> showLicenses(String client);
}
