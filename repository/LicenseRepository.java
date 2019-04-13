package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License,String> {
    Optional<License> findByName(String name);
    List<License> findAll();
}
