package com.detelin.caseforce.service;

import com.detelin.caseforce.GlobalConstants;
import com.detelin.caseforce.domain.entities.Role;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.UserStatus;
import com.detelin.caseforce.domain.models.service.UserServiceModel;
import com.detelin.caseforce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDB();
        if(this.userRepository.count()==0){
            userServiceModel.setAuthorities(this.roleService.findAllRoles());
        }else {
            userServiceModel.setAuthorities(new LinkedHashSet<>());
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority(GlobalConstants.CLIENT_ROLE));
        }

        User user = this.mapper.map(userServiceModel,User.class);
        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setCreated(LocalDate.now());
        return this.mapper.map(this.userRepository.saveAndFlush(user),UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("Username not found")
        );
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {

        return this.userRepository.findByUsername(username).map(u->this.mapper.map(u,UserServiceModel.class))
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll().stream().map(u -> this.mapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return this.userRepository.findById(id).map(u->this.mapper.map(u,UserServiceModel.class))
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    @Override
    public void setUserRole(String id, String role) {
        UserServiceModel userServiceModel = this.mapper.map(
                this.userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"))
                ,UserServiceModel.class);
        userServiceModel.getAuthorities().clear();
        userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_"+role));
        User user = this.mapper.map(userServiceModel,User.class);
        this.userRepository.saveAndFlush(user);


    }

    @Override
    public void setStatus(String id, String status) {
        UserServiceModel userServiceModel = this.mapper.map(
                this.userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found"))
                ,UserServiceModel.class);

        userServiceModel.setStatus(UserStatus.valueOf(status));
        User user = this.mapper.map(userServiceModel,User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password!");
        }

        user.setPassword(!"".equals(userServiceModel.getPassword()) ?
                this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()) :
                user.getPassword());
        user.setEmail(userServiceModel.getEmail());
        user.setImageUrl(userServiceModel.getImageUrl());

        return this.mapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findAnActiveUserToAssignACase() {
        User user = this.userRepository.findUserByStatusEqualsAndAuthoritiesContains(UserStatus.ACTIVE,
                this.mapper.map(this.roleService.findByAuthority(GlobalConstants.TSE_ROLE), Role.class)).orElseThrow();
        return this.mapper.map(user, UserServiceModel.class);
    }
}
