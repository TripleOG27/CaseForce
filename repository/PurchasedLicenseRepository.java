package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.License;
import com.detelin.caseforce.domain.entities.PurchasedLicense;
import com.detelin.caseforce.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedLicenseRepository extends JpaRepository<PurchasedLicense,String> {
    List<License> findAllByPurchasedDateIsNull();
    List<PurchasedLicense> findAllByClientsContains(User username);
}
