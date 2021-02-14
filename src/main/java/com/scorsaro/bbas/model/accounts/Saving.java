package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.dto.accounts.SavingDTO;
import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Saving extends Account {
    public static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.0025);
    public static final Money DEFAULT_MINIMUM_BALANCE = new Money(BigDecimal.valueOf(1000));
    private String secretKey;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minimum_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minimum_balance_amount")),

    })
    private Money minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate;

    public Saving() {
    }

    public Saving(AccountHolder primaryOwner,
                  AccountHolder secondaryOwner,
                  String secretKey,
                  Money minimumBalance,
                  BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;

        this.status = Status.ACTIVE;
        this.interestRate = interestRate;
        applyDefaultValues();
    }

    private void applyDefaultValues() {
        if (this.interestRate == null) {
            this.interestRate = DEFAULT_INTEREST_RATE;
        }
        if (this.minimumBalance == null) {
            this.minimumBalance = DEFAULT_MINIMUM_BALANCE;
        }
    }

    public static Saving parseFromSavingDto(AccountHolder primaryOwner,
                                            AccountHolder secondaryOwner,
                                            SavingDTO savingDTO) {
        return new Saving(primaryOwner,
                secondaryOwner,
                savingDTO.getSecretKey(),
                new Money(savingDTO.getMinimumBalance()),
                savingDTO.getInterestRate()
        );
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
