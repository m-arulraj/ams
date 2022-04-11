package com.abcbank.ams.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "TRANSACTION_DETAILS")
public class Transaction {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence_generator",
            sequenceName = "transaction_sequence",
            initialValue = 10000000,
            allocationSize = 1
    )
    @GeneratedValue(generator = "transaction_sequence_generator")
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TRANSACTION_DATE")
    private Date transactionDate;

    @Column(name = "SENDER_ACCOUNT_NUMBER")
    private Long senderAccountNumber;

    @Column(name = "RECEIVER_ACCOUNT_NUMBER")
    private Long receiverAccountNumber;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "REMARKS", length = 1000)
    private String remarks;

}
