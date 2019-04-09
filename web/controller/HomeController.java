package com.detelin.caseforce.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
//    @PageTitle("Index")
    public ModelAndView index() {
        return super.view("index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
//    @PageTitle("Home")
    public ModelAndView home() {


        return super.view("home");
    }
}