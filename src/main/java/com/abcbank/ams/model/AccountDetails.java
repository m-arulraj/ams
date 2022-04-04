package com.abcbank.ams.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountDetails {

    private Long accountNumber;
    private String accountHolderName;
    private Double accountBalance;
    private List<TransactionDetails> transactionDetails;

}
