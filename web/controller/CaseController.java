package com.detelin.caseforce.web.controller;

import com.detelin.caseforce.domain.entities.enums.CaseStatus;
import com.detelin.caseforce.domain.models.binding.CaseCreateBindingModel;
import com.detelin.caseforce.service.CaseService;
import com.detelin.caseforce.service.CommentService;
import com.detelin.caseforce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/cases")
public class CaseController extends BaseController {
    private final ModelMapper mapper;
    private final CaseService caseService;
    private final CommentService commentService;
    private final ProductService productService;

    public CaseController(ModelMapper mapper, CaseService caseService, CommentService commentService, ProductService productService) {
        this.mapper = mapper;
        this.caseService = caseService;
        this.commentService = commentService;
        this.productService = productService;
    }
    @GetMapping("/open")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView createCase(ModelAndView modelAndView, Principal principal){
        modelAndView.addObject("products",this.productService.findAllNames());

        return super.view("case/open",modelAndView);

    }
    @PostMapping("/open")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ModelAndView createCaseConfirm(Principal principal, @ModelAttribute CaseCreateBindingModel model){

        model.setCustomer(principal.getName());
        this.caseService.createCase(model);
        return super.redirect("/home");
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewCase(@PathVariable long id,ModelAndView modelAndView){
        modelAndView.addObject("case",this.caseService.viewCase(id));
        return super.view("case/view",modelAndView);
    }
    @GetMapping("/view_open")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_TSE')")
    public ModelAndView viewOpenCases(ModelAndView modelAndView, Principal principal){
        modelAndView.addObject("cases",this.caseService.viewAllMyCasesByStatus(CaseStatus.ACTIVE.toString(),principal.getName()));
        return super.view("case/all",modelAndView);

    }
    @GetMapping("/view_closed")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_TSE')")
    public ModelAndView viewClosedCases(ModelAndView modelAndView, Principal principal){
        modelAndView.addObject("cases",this.caseService.viewAllMyCasesByStatus(CaseStatus.CLOSED.toString(),principal.getName()));
        return super.view("case/all",modelAndView);

    }
}
