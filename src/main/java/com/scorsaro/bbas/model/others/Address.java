package com.scorsaro.bbas.model.others;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String number;
    private String details;
    private String country;

    public Address(String street, String number, String country) {
        this.street = street;
        this.number = number;
        this.country = country;
    }

    public Address(String street, String number, String details, String country) {
        this.street = street;
        this.number = number;
        this.details = details;
        this.country = country;
    }

    public Address() {

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
