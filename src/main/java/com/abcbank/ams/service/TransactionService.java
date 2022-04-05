package com.abcbank.ams.service;

import com.abcbank.ams.model.TransactionDetails;

import java.text.ParseException;
import java.util.List;

public interface TransactionService {

    void createTransaction(TransactionDetails transactionDetails);
    List<TransactionDetails> getTransactions(Long userId, String from, String to) throws ParseException;

}
