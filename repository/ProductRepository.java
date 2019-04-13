package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    Optional<Product> findByName(String name);
}
