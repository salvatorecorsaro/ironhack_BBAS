package com.scorsaro.bbas.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scorsaro.bbas.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.others.Address;
import com.scorsaro.bbas.model.others.Name;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolder extends User {
    @Embedded
    private Name name;
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private String email;

//    @OneToOne
//    private AccountUser accountUser;

    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    private List<Account> primaryAccounts = new ArrayList<>();
    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    private List<Account> secondaryAccounts = new ArrayList<>();


    public AccountHolder() {
    }

    public AccountHolder(String username, String password, Name name, LocalDate dateOfBirth, Address primaryAddress) {
        super(username, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder(String username, String password, Name name, LocalDate dateOfBirth, Address primaryAddress, String email) {
        super(username, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.email = email;
    }

    public static AccountHolder parseFromAccountHolderDTO(AccountHolderDTO accountHolderDTO) {
        return new AccountHolder(accountHolderDTO.getUsername(),
                accountHolderDTO.getPassword(),
                accountHolderDTO.getName(),
                accountHolderDTO.getDateOfBirth(),
                accountHolderDTO.getPrimaryAddress(),
                accountHolderDTO.getEmail());
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Account> getPrimaryAccounts() {
        return primaryAccounts;
    }

    public void setPrimaryAccounts(List<Account> primaryAccounts) {
        this.primaryAccounts = primaryAccounts;
    }

    public List<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }

    public void setSecondaryAccounts(List<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
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
