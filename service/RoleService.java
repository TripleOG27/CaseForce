package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDB();

    Set<RoleServiceModel> findAllRoles();
    RoleServiceModel findByAuthority(String authority);
}
