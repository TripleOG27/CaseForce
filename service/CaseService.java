package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.binding.CaseCreateBindingModel;
import com.detelin.caseforce.domain.models.service.CaseServiceModel;
import com.detelin.caseforce.domain.models.view.CaseViewModel;

import java.util.List;

public interface CaseService {
    CaseServiceModel createCase(CaseCreateBindingModel model);
    CaseViewModel createCaseView(String clientName);
    CaseViewModel viewCase(long id);
    List<CaseViewModel> viewAllMyCasesByStatus(String status,String name);
}
