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

    @Column(name = "DOOR_NUMBER")
    private String doorNumber;

    @Column(name = "STREET")
    private String street;

    @Column(name = "AREA")
    private String area;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "PINCODE")
    private Integer pincode;

}
