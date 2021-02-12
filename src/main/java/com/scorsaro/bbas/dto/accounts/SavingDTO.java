package com.scorsaro.bbas.dto.accounts;

import java.math.BigDecimal;
import java.util.Optional;

public class SavingDTO extends AccountDTO {
    private Optional<String> secretKey;
    private Optional<BigDecimal> minimumBalance;
    private Optional<String> status;
    private Optional<BigDecimal> interestRate;

    public SavingDTO() {
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

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }

    public Optional<BigDecimal> getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Optional<BigDecimal> interestRate) {
        this.interestRate = interestRate;
    }
}
