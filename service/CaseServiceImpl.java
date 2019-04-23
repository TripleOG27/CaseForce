package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.entities.Case;
import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.entities.enums.CaseStatus;
import com.detelin.caseforce.domain.models.binding.CaseCreateBindingModel;
import com.detelin.caseforce.domain.models.service.CaseServiceModel;
import com.detelin.caseforce.domain.models.view.CaseViewModel;
import com.detelin.caseforce.repository.CaseRepository;
import com.detelin.caseforce.repository.SubcategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final ProductService productService;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository, UserService userService, ModelMapper mapper, ProductService productService, SubcategoryRepository subcategoryRepository) {
        this.caseRepository = caseRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.productService = productService;

    }

    @Override
    public CaseServiceModel createCase(CaseCreateBindingModel model) {
        User client = this.mapper.map(this.userService.findUserByUsername(model.getCustomer()),User.class);
        CaseServiceModel caseServiceModel = this.mapper.map(model,CaseServiceModel.class);
        caseServiceModel.setCategory(this.productService.findProductByName(model.getCategory()));
        caseServiceModel.setSubcategory(this.productService.findSubcategoryOfProduct(model.getCategory(),model.getSubcategory()));
        caseServiceModel.setComments(new LinkedList<>());
        caseServiceModel.setCustomer(client);
        caseServiceModel.setOpenedTime(LocalDateTime.now());
        caseServiceModel.setOwner(this.mapper.map(this.userService.findAnActiveUserToAssignACase(),User.class));
        caseServiceModel.setStatus(CaseStatus.ACTIVE);
        Case _case = this.mapper.map(caseServiceModel, Case.class);
        this.caseRepository.saveAndFlush(_case);

        return caseServiceModel;
    }

    @Override
    public CaseViewModel createCaseView(String clientName) {
        CaseViewModel viewModel = new CaseViewModel();
        return null;
    }

    @Override
    public CaseViewModel viewCase(long id) {
        return this.mapper.map(this.caseRepository.findById(id).orElseThrow(),CaseViewModel.class);

    }

    @Override
    public List<CaseViewModel> viewAllMyCasesByStatus(String status, String name) {
        User owner = this.mapper.map(this.userService.findUserByUsername(name),User.class);
        return this.caseRepository.findAllByStatusEqualsAndOwner(CaseStatus.valueOf(status),owner).stream()
                .map(c-> this.mapper.map(c,CaseViewModel.class)).collect(Collectors.toList());
    }
}
