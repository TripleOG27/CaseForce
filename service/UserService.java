package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
    UserServiceModel findUserByUsername(String username);
    UserServiceModel findUserById(String id);
    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);
    void setStatus(String id,String status);
    UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);
}
