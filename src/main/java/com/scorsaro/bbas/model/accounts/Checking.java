package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.dto.accounts.CheckingDTO;
import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Checking extends Account {
    public static final Money MINIMUM_BALANCE = new Money(BigDecimal.valueOf(250));
    public static final Money MONTHLY_MAINTENANCE_FEE = new Money(BigDecimal.valueOf(12));
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


    public static Checking parseFromCheckingDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner, CheckingDTO checkingDTO) {
        return new Checking(
                primaryOwner,
                secondaryOwner,
                checkingDTO.getSecretKey()
        );
    }

    public Checking(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner);
        this.secretKey = secretKey;
    }

    public Checking(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        setStatus(Status.ACTIVE);
        setMinimumBalance(MINIMUM_BALANCE);
        setMonthlyMaintenanceFee(MONTHLY_MAINTENANCE_FEE);
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
