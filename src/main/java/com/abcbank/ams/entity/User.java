package com.abcbank.ams.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "GENDER", length = 1)
    private String gender;

    @Column(name = "PAN_NUMBER", length = 10)
    private String panNumber;

    @Column(name = "PHONE_NUMBER", length = 10)
    private Long phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "EMAIL_ID")
    private UserCredentials userCredentials;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_NUMBER")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address currentAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address permanentAddress;

}
