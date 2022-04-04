package com.abcbank.ams.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetails {

    private Long userId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String panNumber;
    private String emailId;
    private String password;
    private UserAddress currentAddress;
    private UserAddress permanentAddress;

}
