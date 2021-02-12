package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.model.accounts.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AccountDTO {
    protected String accountType;
    protected Long id;
    protected Long primaryOwner;
    protected Long secondaryOwner;
    protected BigDecimal balance;
    protected BigDecimal penaltyFee;
    protected LocalDate creationDate;
    private Optional<String> secretKey;
    private Optional<BigDecimal> minimumBalance;
    private Optional<BigDecimal> monthlyMaintenanceFee;
    private Optional<String> status;
    private Optional<BigDecimal> creditLimit;
    private Optional<BigDecimal> interestRate;

    public static AccountDTO parseFromAccount(Account account) {
        AccountDTO retAccount = new AccountDTO();
        String accountType = "";
        if (account instanceof Checking)
            accountType = "Checking";
        else if (account instanceof CreditCard)
            accountType = "CreditCard";
        else if (account instanceof Saving)
            accountType = "Saving";
        else if (account instanceof Student)
            accountType = "Student";
        retAccount.setAccountType(accountType);
        retAccount.setId(account.getId());
        retAccount.setPrimaryOwner(account.getPrimaryOwner().getId());
        retAccount.setSecondaryOwner(account.getSecondaryOwner().getId());
        retAccount.setBalance(account.getBalance().getAmount());
        retAccount.setBalance(account.getPenaltyFee().getAmount());
        retAccount.setCreationDate(account.getCreationDate());
        switch (accountType) {
            case "Checking" -> {
                retAccount.setSecretKey(Optional.ofNullable(((Checking) account).getSecretKey()));
                retAccount.setMinimumBalance(Optional.ofNullable(((Checking) account).getMinimumBalance().getAmount()));
                retAccount.setMonthlyMaintenanceFee(Optional.ofNullable(((Checking) account).getMonthlyMaintenanceFee().getAmount()));
                retAccount.setStatus(Optional.of(((Checking) account).getStatus().name()));
            }
            case "CreditCard" -> {
                retAccount.setCreditLimit(Optional.ofNullable(((CreditCard) account).getCreditLimit().getAmount()));
                retAccount.setInterestRate(Optional.ofNullable(((CreditCard) account).getInterestRate()));
            }
            case "Saving" -> {
                retAccount.setSecretKey(Optional.ofNullable(((Saving) account).getSecretKey()));
                retAccount.setMinimumBalance(Optional.ofNullable(((Saving) account).getMinimumBalance().getAmount()));
                retAccount.setStatus(Optional.of(((Saving) account).getStatus().name()));
                retAccount.setInterestRate(Optional.ofNullable(((Saving) account).getInterestRate()));
            }
            case "Student" -> {
                retAccount.setSecretKey(Optional.ofNullable(((Student) account).getSecretKey()));
                retAccount.setStatus(Optional.of(((Student) account).getStatus().name()));
            }
        }

        return retAccount;
    }

    public AccountDTO() {
    }

    public AccountDTO(String accountType, Long id, BigDecimal balance, BigDecimal penaltyFee, LocalDate creationDate) {
        this.accountType = accountType;
        this.id = id;
        this.balance = balance;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Long getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Long secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Optional<String> getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Optional<String> secretKey) {
        this.secretKey = secretKey;
    }

    public Optional<BigDecimal> getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Optional<BigDecimal> minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Optional<BigDecimal> getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Optional<BigDecimal> monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }

    public Optional<BigDecimal> getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Optional<BigDecimal> creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Optional<BigDecimal> getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Optional<BigDecimal> interestRate) {
        this.interestRate = interestRate;
    }
}
