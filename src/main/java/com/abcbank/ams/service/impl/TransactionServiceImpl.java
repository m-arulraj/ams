package com.abcbank.ams.service.impl;

import com.abcbank.ams.common.constants.AmsConstants;
import com.abcbank.ams.entity.Account;
import com.abcbank.ams.entity.Transaction;
import com.abcbank.ams.entity.User;
import com.abcbank.ams.mapper.TransactionMapper;
import com.abcbank.ams.model.TransactionDetails;
import com.abcbank.ams.repository.AccountRepository;
import com.abcbank.ams.repository.TransactionRepository;
import com.abcbank.ams.repository.UserRepository;
import com.abcbank.ams.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createTransaction(TransactionDetails transactionDetails) {
        Account senderAccount = accountRepository.getById(transactionDetails.getSenderAccountNumber());
        Double senderAccountBalance = senderAccount.getCurrentBalance();
        senderAccountBalance = senderAccountBalance - transactionDetails.getAmount();
        senderAccount.setCurrentBalance(senderAccountBalance);
        accountRepository.save(senderAccount);
        log.info("Rs.{} debited from Account ID #{}", transactionDetails.getAmount(), transactionDetails.getSenderAccountNumber());

        Account receiverAccount = accountRepository.getById(transactionDetails.getReceiverAccountNumber());
        Double receiverAccountBalance = receiverAccount.getCurrentBalance();
        receiverAccountBalance = receiverAccountBalance + transactionDetails.getAmount();
        receiverAccount.setCurrentBalance(receiverAccountBalance);
        accountRepository.save(receiverAccount);
        log.info("Rs.{} credited to Account ID #{}", transactionDetails.getAmount(), transactionDetails.getReceiverAccountNumber());

        Transaction transaction = transactionRepository.save(TransactionMapper.INSTANCE.map(transactionDetails));
        log.info("Transaction done successfully with ID #{}", transaction.getTransactionId());
    }

    @Override
    public List<TransactionDetails> getTransactions(Long userId, String from, String to) throws ParseException {
        Date fromDate = new SimpleDateFormat(AmsConstants.COMMON_DATE_FORMAT).parse(from);
        Date toDate =  new SimpleDateFormat(AmsConstants.COMMON_DATE_FORMAT).parse(to);
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(toDate);
        calendar.add(Calendar.DATE,1);
        toDate = calendar.getTime();

        User user = userRepository.getById(userId);
        Long userAccountNumber = user.getAccount().getAccountNumber();
        List<Transaction> transactions =
                transactionRepository.findTransactionsByReceiverAccountNumberOrSenderAccountNumberAndTransactionDateBetween(
                        userAccountNumber, userAccountNumber, fromDate, toDate);

        return TransactionMapper.INSTANCE.map(transactions);
    }

}
