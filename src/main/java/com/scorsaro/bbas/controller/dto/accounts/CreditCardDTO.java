package com.scorsaro.bbas.controller.dto.accounts;

import java.math.BigDecimal;

public class CreditCardDTO extends AccountDTO {
    private BigDecimal creditLimit;
    private BigDecimal interestRate;

    public CreditCardDTO() {
        super();
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
