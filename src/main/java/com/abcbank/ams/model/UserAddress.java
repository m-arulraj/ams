package com.abcbank.ams.model;

import lombok.Data;

@Data
public class UserAddress {

    private String doorNumber;
    private String street;
    private String area;
    private String city;
    private String state;
    private Integer pincode;

}
