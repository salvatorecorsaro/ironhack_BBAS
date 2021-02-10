package com.scorsaro.bbas.controller.dto.users;

import com.scorsaro.bbas.controller.dto.others.AddressDTO;
import com.scorsaro.bbas.controller.dto.others.NameDTO;

import java.util.Optional;

public class AccountHolderDTO {
    private Optional<String> id;
    private Optional<NameDTO> name;
    private Optional<String> dateOfBirth;
    private Optional<AddressDTO> primaryAddress;
    private Optional<String> email;

    public AccountHolderDTO() {
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<NameDTO> getName() {
        return name;
    }

    public void setName(Optional<NameDTO> name) {
        this.name = name;
    }

    public Optional<String> getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Optional<String> dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Optional<AddressDTO> getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Optional<AddressDTO> primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }
}
