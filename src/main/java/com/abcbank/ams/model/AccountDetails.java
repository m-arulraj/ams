package com.abcbank.ams.model;

import lombok.Data;

@Data
public class AccountDetails {

    private Long accountNumber;
    private String accountHolderName;
    private Double accountBalance;

}
