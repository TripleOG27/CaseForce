package com.detelin.caseforce.service;

import com.detelin.caseforce.GlobalConstants;
import com.detelin.caseforce.domain.entities.Role;
import com.detelin.caseforce.domain.models.service.RoleServiceModel;
import com.detelin.caseforce.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedRolesInDB() {
        if(this.roleRepository.count()==0){
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.CLIENT_ROLE ));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.TSE_ROLE));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.TSM_ROLE));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.ACCOUNT_MANAGER_ROLE));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.PRIVILEGES_ROLE));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.DEVELOPER_ROLE));
            this.roleRepository.saveAndFlush(new Role(GlobalConstants.ROOT_ROLE));
        }
    }


    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleRepository.findAll().stream()
                .map(r->this.mapper.map(r,RoleServiceModel.class)).collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.mapper.map(this.roleRepository.findByAuthority(authority),RoleServiceModel.class);
    }
}
