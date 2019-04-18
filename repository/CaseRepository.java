package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case,Integer> {
}
