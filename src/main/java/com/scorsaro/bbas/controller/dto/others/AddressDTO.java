package com.scorsaro.bbas.controller.dto.others;

import java.util.Optional;

public class AddressDTO {
    private Optional<String> street;
    private Optional<String> number;
    private Optional<String> details;
    private Optional<String> country;

    public AddressDTO() {
    }

    public Optional<String> getStreet() {
        return street;
    }

    public void setStreet(Optional<String> street) {
        this.street = street;
    }

    public Optional<String> getNumber() {
        return number;
    }

    public void setNumber(Optional<String> number) {
        this.number = number;
    }

    public Optional<String> getDetails() {
        return details;
    }

    public void setDetails(Optional<String> details) {
        this.details = details;
    }

    public Optional<String> getCountry() {
        return country;
    }

    public void setCountry(Optional<String> country) {
        this.country = country;
    }
}
