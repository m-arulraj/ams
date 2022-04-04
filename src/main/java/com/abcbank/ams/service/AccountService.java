package com.abcbank.ams.service;

import com.abcbank.ams.model.AccountDetails;

import java.util.List;

public interface AccountService {

    AccountDetails getAccountSummary(Long userId);
    List<AccountDetails> getAllAccounts();

}
