package com.abcbank.ams.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DOOR_NUMBER", length = 50)
    private String doorNumber;

    @Column(name = "STREET", length = 50)
    private String street;

    @Column(name = "AREA", length = 50)
    private String area;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "STATE", length = 2)
    private String state;

    @Column(name = "PINCODE", length = 6)
    private Integer pincode;

}
