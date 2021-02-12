package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.dto.accounts.CreditCardDTO;
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

    public CreditCard(AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, BigDecimal interestRate) {
        super(primaryOwner, secondaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public static CreditCard parseFromCreditCardDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner, CreditCardDTO creditCardDTO) {
        return new CreditCard(primaryOwner,
                secondaryOwner,
                new Money(creditCardDTO.getCreditLimit()),
                creditCardDTO.getInterestRate());
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
