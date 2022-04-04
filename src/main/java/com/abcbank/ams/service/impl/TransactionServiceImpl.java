package com.abcbank.ams.service.impl;

import com.abcbank.ams.entity.Account;
import com.abcbank.ams.entity.Transaction;
import com.abcbank.ams.mapper.TransactionMapper;
import com.abcbank.ams.model.TransactionDetails;
import com.abcbank.ams.repository.AccountRepository;
import com.abcbank.ams.repository.TransactionRepository;
import com.abcbank.ams.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void createTransaction(TransactionDetails transactionDetails) {
        Account senderAccount = accountRepository.getById(transactionDetails.getSenderAccountNumber());
        Double senderAccountBalance = senderAccount.getAccountBalance();
        senderAccountBalance = senderAccountBalance - transactionDetails.getAmount();
        senderAccount.setAccountBalance(senderAccountBalance);
        accountRepository.save(senderAccount);
        log.info("Rs.{} debited from Account ID #{}", transactionDetails.getAmount(), transactionDetails.getSenderAccountNumber());

        Account receiverAccount = accountRepository.getById(transactionDetails.getReceiverAccountNumber());
        Double receiverAccountBalance = receiverAccount.getAccountBalance();
        receiverAccountBalance = receiverAccountBalance + transactionDetails.getAmount();
        receiverAccount.setAccountBalance(receiverAccountBalance);
        accountRepository.save(receiverAccount);
        log.info("Rs.{} credited to Account ID #{}", transactionDetails.getAmount(), transactionDetails.getReceiverAccountNumber());

        Transaction transaction = transactionRepository.save(TransactionMapper.INSTANCE.map(transactionDetails));
        log.info("Transaction done successfully with ID #{}", transaction.getTransactionId());
    }

}
