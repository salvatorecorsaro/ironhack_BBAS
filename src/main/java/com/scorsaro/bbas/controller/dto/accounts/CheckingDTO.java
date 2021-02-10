package com.scorsaro.bbas.controller.dto.accounts;

import java.math.BigDecimal;
import java.util.Optional;

public class CheckingDTO extends AccountDTO {
    private Optional<String> secretKey;
    private Optional<BigDecimal> minimumBalance;
    private Optional<BigDecimal> monthlyMaintenanceFee;
    private Optional<String> status;

    public CheckingDTO() {
        super();
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
}
