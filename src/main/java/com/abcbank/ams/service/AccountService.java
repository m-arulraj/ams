package com.abcbank.ams.service;

import com.abcbank.ams.model.AccountDetails;
import com.abcbank.ams.model.BeneficiaryDetails;

import java.util.List;

public interface AccountService {

    AccountDetails getAccountSummary(Long userId);
    List<BeneficiaryDetails> getAllBeneficiaries();

}
