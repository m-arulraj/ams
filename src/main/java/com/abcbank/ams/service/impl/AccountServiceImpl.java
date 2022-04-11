package com.abcbank.ams.service.impl;

import com.abcbank.ams.entity.Account;
import com.abcbank.ams.entity.User;
import com.abcbank.ams.mapper.AccountMapper;
import com.abcbank.ams.mapper.BeneficiaryMapper;
import com.abcbank.ams.model.AccountDetails;
import com.abcbank.ams.model.BeneficiaryDetails;
import com.abcbank.ams.repository.AccountRepository;
import com.abcbank.ams.repository.UserRepository;
import com.abcbank.ams.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDetails getAccountSummary(Long userId) {
        User user = userRepository.getById(userId);
        return AccountMapper.INSTANCE.map(user.getAccount());
    }

    @Override
    public List<BeneficiaryDetails> getAllBeneficiaries() {
        List<Account> accounts = accountRepository.findAll();
        return BeneficiaryMapper.INSTANCE.map(accounts);
    }

}
