package com.example.test30.models;

import lombok.Data;

@Data
public class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private String position;
    private String officeNumber;
    private String phoneNumber;
    public Person(String firstName, String lastName, String middleName, String position, String officeNumber, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.officeNumber = officeNumber;
        this.phoneNumber = phoneNumber;
    }
}
