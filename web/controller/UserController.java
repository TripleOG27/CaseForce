package com.detelin.caseforce.web.controller;

import com.cloudinary.Cloudinary;
import com.detelin.caseforce.domain.entities.enums.UserStatus;
import com.detelin.caseforce.domain.models.binding.UserEditBindingModel;
import com.detelin.caseforce.domain.models.binding.UserRegisterBindingModel;
import com.detelin.caseforce.domain.models.service.RoleServiceModel;
import com.detelin.caseforce.domain.models.service.UserServiceModel;
import com.detelin.caseforce.domain.models.view.UserAllViewModel;
import com.detelin.caseforce.domain.models.view.UserProfileViewModel;
import com.detelin.caseforce.service.CloudService;
import com.detelin.caseforce.service.RoleService;
import com.detelin.caseforce.service.UserService;
import com.detelin.caseforce.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CloudService cloudService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, RoleService roleService, CloudService cloud) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.cloudService = cloud;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Register")
    public ModelAndView register() {
        return super.view("register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel model) {
        if (!model.getPassword().equals(model.getConfirmPassword())) {
            return super.view("register");
        }

        this.userService.registerUser(this.modelMapper.map(model, UserServiceModel.class));

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Login")
    public ModelAndView login() {
        return super.view("login");
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit Profile")
    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
        modelAndView
                .addObject("user", this.modelMapper.map(this.userService.findUserByUsername(principal.getName()), UserProfileViewModel.class));

        return super.view("user/edit-profile", modelAndView);
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel user) throws IOException {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return super.view("user/edit-profile");
        }
        UserServiceModel userServiceModel = this.modelMapper.map(user,UserServiceModel.class);
        userServiceModel.setImageUrl(this.cloudService.uploadImage(user.getImageUrl()));
        this.userService.editUserProfile(userServiceModel, user.getOldPassword());

        return super.redirect("/home");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_TSM','ROLE_PRIVILEGES')")
    @PageTitle("All Users")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    user.setAuthorities(u.getAuthorities().stream().map(RoleServiceModel::getAuthority).collect(Collectors.toSet()));
                    user.setHighestAuthority();
                    return user;
                })
                .collect(Collectors.toList());

        modelAndView.addObject("users", users);

        return super.view("user/all-users", modelAndView);
    }
    @GetMapping("/privilege/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PRIVILEGES')")
    public ModelAndView setUserRole(@PathVariable String id,ModelAndView modelAndView) {
       modelAndView.addObject("user",this.modelMapper.map(this.userService.findUserById(id),UserAllViewModel.class));
       modelAndView.addObject("roles",this.roleService.findAllRoles().stream().map(r->r.getAuthority().substring(5)).collect(Collectors.toList()));
        return super.view("/user/edit-roles",modelAndView);
    }
    @PostMapping("/privilege/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PRIVILEGES')")
    public ModelAndView setUserRole(@PathVariable String id,@RequestParam(name = "authorities",required = false)String role ) {

        this.userService.setUserRole(id, role);

        return super.redirect("/users/all");
    }
    @GetMapping("/status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TSM')")
    public ModelAndView setUserStatus(@PathVariable String id,ModelAndView modelAndView) {
        modelAndView.addObject("user",this.modelMapper.map(this.userService.findUserById(id),UserAllViewModel.class));
        modelAndView.addObject("status", UserStatus.values());
        return super.view("/user/edit-status",modelAndView);
    }
    @PostMapping("/status/{id}")
    @PreAuthorize("hasAnyRole('ROLE_TSM')")
    public ModelAndView setUserStatus(@PathVariable String id,@RequestParam(name = "status",required = false)String status ) {
        int b=5;
        this.userService.setStatus(id, status);

        return super.redirect("/users/all");
    }


}
