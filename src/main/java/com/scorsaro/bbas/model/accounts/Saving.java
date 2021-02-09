package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
public class Saving extends Account {
    private String secretKey;
    private BigDecimal minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate;

    public Saving() {
    }

    public Saving(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
