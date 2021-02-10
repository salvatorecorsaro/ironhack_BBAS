package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.*;

@Entity
public class Checking extends Account {
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),

    })
    private Money minimumBalance;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "monthy_fee_amount")),

    })
    private Money monthlyMaintenanceFee;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Checking() {
    }

    public Checking(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
