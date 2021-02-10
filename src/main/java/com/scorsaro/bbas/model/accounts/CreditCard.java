package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CreditCard extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount")),

    })
    private Money creditLimit;
    private BigDecimal interestRate;

    public CreditCard() {
    }

    public CreditCard(AccountHolder primaryOwner) {
        super(primaryOwner);
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }


}
