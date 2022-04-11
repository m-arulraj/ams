package com.abcbank.ams.model;

import lombok.Data;
import java.util.Date;

@Data
public class TransactionDetails {

    private Long transactionId;
    private Date transactionDate;
    private Long senderAccountNumber;
    private Long receiverAccountNumber;
    private Double amount;
    private String remarks;

}
