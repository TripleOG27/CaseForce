package com.detelin.caseforce.web.controller;

import com.detelin.caseforce.domain.models.binding.UserRegisterBindingModel;
import com.detelin.caseforce.domain.models.service.RoleServiceModel;
import com.detelin.caseforce.domain.models.service.UserServiceModel;
import com.detelin.caseforce.domain.models.view.UserAllViewModel;
import com.detelin.caseforce.service.RoleService;
import com.detelin.caseforce.service.UserService;
import com.detelin.caseforce.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, RoleService roleService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
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

//    @GetMapping("/profile")
//    @PreAuthorize("isAuthenticated()")
////    @PageTitle("Profile")
//    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
//        modelAndView
//                .addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));
//
//        return super.view("profile", modelAndView);
//    }
//
//    @GetMapping("/edit")
//    @PreAuthorize("isAuthenticated()")
//    @PageTitle("Edit Profile")
//    public ModelAndView editProfile(Principal principal, ModelAndView modelAndView) {
//        modelAndView
//                .addObject("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));
//
//        return super.view("edit-profile", modelAndView);
//    }
//
//    @PatchMapping("/edit")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView editProfileConfirm(@ModelAttribute UserEditBindingModel model) {
//        if (!model.getPassword().equals(model.getConfirmPassword())) {
//            return super.view("edit-profile");
//        }
//
//        this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());
//
//        return super.redirect("/users/profile");
//    }
//
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
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PRIVILEGES')")
    public ModelAndView setUserRole(@PathVariable String id,ModelAndView modelAndView) {
       modelAndView.addObject("user",this.modelMapper.map(this.userService.findUserById(id),UserAllViewModel.class));
        return super.view("/user/edit-roles",modelAndView);
    }
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PRIVILEGES')")
    public ModelAndView setUser(@PathVariable String id) {
        this.userService.setUserRole(id, "user");

        return super.redirect("/users/all");
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ROLE_PRIVILEGES')")
    @ResponseBody
    public List<String> findAllRoles() {
        List<String>authorities=new LinkedList<>();
        this.roleService.findAllRoles().forEach(r->((LinkedList<String>) authorities).push(r.getAuthority()));

        return authorities;
    }

//    @PostMapping("/set-admin/{id}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ModelAndView setAdmin(@PathVariable String id) {
//        this.userService.setUserRole(id, "admin");
//
//        return super.redirect("/users/all");
//    }
}
