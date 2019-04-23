package com.detelin.caseforce.repository;

import com.detelin.caseforce.domain.entities.Role;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.CaseStatus;
import com.detelin.caseforce.domain.entities.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(String id);
    Optional<User> findUserByStatusEqualsAndAuthoritiesContains(UserStatus status, Role role);
}
