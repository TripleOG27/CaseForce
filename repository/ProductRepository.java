package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.detelin.caseforce.domain.entities.enums.ProductStatus.EVS;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Optional<Product> findByName(String name);
    List<Product> findAllByAvailabilityNotLike(String productStatus);
}
