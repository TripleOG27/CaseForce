package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.entities.User;
import com.detelin.caseforce.domain.models.binding.CaseCreateBindingModel;
import com.detelin.caseforce.domain.models.service.CaseServiceModel;
import com.detelin.caseforce.domain.models.view.CaseViewModel;
import com.detelin.caseforce.repository.CaseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository, UserService userService, ModelMapper mapper) {
        this.caseRepository = caseRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public CaseServiceModel createCase(String clientName, CaseCreateBindingModel model) {
        User user = this.mapper.map(this.userService.findUserByUsername(clientName),User.class);
        CaseServiceModel caseServiceModel = this.mapper.map(model,CaseServiceModel.class);

        return null;
    }

    @Override
    public CaseViewModel createCaseView(String clientName) {
        CaseViewModel viewModel = new CaseViewModel();
        return null;
    }
}
