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

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DOB")
    private Date dob;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "PAN_NUMBER")
    private String panNumber;

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
