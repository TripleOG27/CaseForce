package com.detelin.caseforce.service;

import com.detelin.caseforce.domain.models.binding.CaseCreateBindingModel;
import com.detelin.caseforce.domain.models.service.CaseServiceModel;
import com.detelin.caseforce.domain.models.view.CaseViewModel;

public interface CaseService {
    CaseServiceModel createCase(String clientName, CaseCreateBindingModel model);
    CaseViewModel createCaseView(String clientName);
}
