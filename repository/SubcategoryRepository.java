package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Product;
import com.detelin.caseforce.domain.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory,String> {
    List<Subcategory> findAllByProduct(Product product);
    Subcategory findByProductAndSubcategory(Product product,String subcategory);

}
