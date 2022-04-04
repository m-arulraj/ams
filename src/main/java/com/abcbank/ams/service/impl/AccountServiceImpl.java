package com.abcbank.ams.service.impl;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.entity.Account;
import com.abcbank.ams.entity.Transaction;
import com.abcbank.ams.entity.User;
import com.abcbank.ams.mapper.AccountMapper;
import com.abcbank.ams.mapper.TransactionMapper;
import com.abcbank.ams.model.AccountDetails;
import com.abcbank.ams.model.TransactionDetails;
import com.abcbank.ams.repository.AccountRepository;
import com.abcbank.ams.repository.TransactionRepository;
import com.abcbank.ams.repository.UserRepository;
import com.abcbank.ams.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AccountDetails getAccountSummary(Long userId) {
        User user = userRepository.getById(userId);
        AccountDetails accountDetails = AccountMapper.INSTANCE.map(user.getAccount());
        List<TransactionDetails> transactionDetailsList =  new ArrayList<>();

        List<Transaction> creditTransactions =
                transactionRepository.findTransactionsByReceiverAccountNumber(user.getAccount().getAccountNumber());
        List<TransactionDetails> creditTransactionDetailsList = TransactionMapper.INSTANCE.map(creditTransactions);
        creditTransactionDetailsList.forEach(t -> t.setType(AmsConstants.TRANSACTION_TYPE_CREDIT));

        List<Transaction> debitTransactions =
                transactionRepository.findTransactionsBySenderAccountNumber(user.getAccount().getAccountNumber());
        List<TransactionDetails> debitTransactionDetailsList = TransactionMapper.INSTANCE.map(debitTransactions);
        debitTransactionDetailsList.forEach(t -> t.setType(AmsConstants.TRANSACTION_TYPE_DEBIT));

        transactionDetailsList.addAll(creditTransactionDetailsList);
        transactionDetailsList.addAll(debitTransactionDetailsList);
        accountDetails.setTransactionDetails(transactionDetailsList);
        return accountDetails;
    }

    @Override
    public List<AccountDetails> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDetails> accountDetailsList = AccountMapper.INSTANCE.map(accounts);
        accountDetailsList.forEach(a -> a.setAccountBalance(null));
        return accountDetailsList;
    }


}
