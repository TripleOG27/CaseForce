package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
    UserServiceModel findUserByUsername(String username);
}
