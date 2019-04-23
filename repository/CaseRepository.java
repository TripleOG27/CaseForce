package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Case;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<Case,Integer> {
    Optional<Case> findById(long id);
    List<Case> findAllByStatusEqualsAndOwner(CaseStatus status, User user);
}
