package com.scorsaro.bbas.dto.others;

import java.math.BigDecimal;

public class BalanceDTO {
    BigDecimal balance;

    public BalanceDTO() {
    }

    public BalanceDTO(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
