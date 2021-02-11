package com.scorsaro.bbas.controller.dto.users;

import com.scorsaro.bbas.model.others.Address;
import com.scorsaro.bbas.model.others.Name;
import com.scorsaro.bbas.model.users.AccountHolder;

import java.time.LocalDate;

public class AccountHolderDTO {
    private Long id;
    private String username;
    private Name name;
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private String email;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(Long id, Name name, LocalDate dateOfBirth, Address primaryAddress) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolderDTO(Long id, Name name, LocalDate dateOfBirth, Address primaryAddress, String email) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.email = email;
    }

    public static AccountHolderDTO parseFromAccountHolder(AccountHolder accountHolder) {
        AccountHolderDTO retAccountHolderDTO = new AccountHolderDTO();
        retAccountHolderDTO.setId(accountHolder.getId());
        retAccountHolderDTO.setUsername(accountHolder.getUsername());
        retAccountHolderDTO.setName(accountHolder.getName());
        retAccountHolderDTO.setDateOfBirth(accountHolder.getDateOfBirth());
        retAccountHolderDTO.setPrimaryAddress(accountHolder.getPrimaryAddress());
        retAccountHolderDTO.setEmail(accountHolder.getEmail());

        return retAccountHolderDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
