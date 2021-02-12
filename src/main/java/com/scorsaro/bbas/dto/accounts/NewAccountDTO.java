package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.model.users.AccountHolder;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

public class NewAccountDTO {
    @Valid
    private BigDecimal amount;
    @Valid
    private String secretKey;
    @Valid
    private AccountHolder primaryOwner;
    @Valid
    private AccountHolder secondaryOwner;
    @Valid
    @Digits(integer = 3, fraction = 2, message = "Maximum 3 integer digits and 2 fraction digits")
    @DecimalMin(value = "0", inclusive = false, message = "MinimumBalance must be grater than 0")
    private BigDecimal minimumBalance;
    @Valid
    @Digits(integer = 0, fraction = 5, message = "Maximum 0 integer digits and 5 fraction digits")
    @DecimalMin(value = "0", inclusive = false, message = "InterestRate can't be negative")
    private BigDecimal interestRate;
    @Valid
    @DecimalMax(value = "100000", inclusive = true, message = "Maximum amount 100000")
    @DecimalMin(value = "100", inclusive = true, message = "Minimum amount 100")
    @DecimalMin(value = "0", inclusive = false, message = "Amount to create an account must be grater than 0")
    private BigDecimal creditLimit;

    public NewAccountDTO() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
