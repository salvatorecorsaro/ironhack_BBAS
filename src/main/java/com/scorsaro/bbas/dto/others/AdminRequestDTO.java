package com.scorsaro.bbas.dto.others;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AdminRequestDTO {
    @Min(value = 1, message = "An account number is required")
    private long accountId;
    @Valid
    @NotNull(message = "Balance is required")
    private BigDecimal balanceOperationValue;

    public AdminRequestDTO() {
    }

    public AdminRequestDTO(long accountId, BigDecimal newBalance) {
        this.accountId = accountId;
        this.balanceOperationValue = newBalance;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalanceOperationValue() {
        return balanceOperationValue;
    }

    public void setBalanceOperationValue(BigDecimal balanceOperationValue) {
        this.balanceOperationValue = balanceOperationValue;
    }
}
