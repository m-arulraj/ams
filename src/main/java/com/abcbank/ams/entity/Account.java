package com.abcbank.ams.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ACCOUNT_DETAILS")
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence_generator",
            sequenceName = "account_sequence",
            initialValue = 1000000000,
            allocationSize = 1
    )
    @GeneratedValue(generator = "account_sequence_generator")
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column(name = "ACCOUNT_HOLDER_NAME", length = 100)
    private String accountHolderName;

    @Column(name = "CURRENT_BALANCE")
    private Double currentBalance;

}
