package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.model.accounts.CreditCard;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardDTO {
    private long id;
    @Valid
    @NotNull(message = "Balance is required")
    private BigDecimal balance;
    @Min(value = 1, message = "A primary owner is required to create an account")
    private Long primaryOwner;
    private Long secondaryOwner;
    @DecimalMin(value = "100", message = "Credit limit must be above 100")
    @DecimalMax(value = "100000", message = "Credit limit must be below 100000")
    private BigDecimal creditLimit;
    @DecimalMin(value = "0.1", message = "Interest rate must be above 0.1")
    @DecimalMax(value = "0.2", message = "Interest rate must be below 0.2")
    private BigDecimal interestRate;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;

    public CreditCardDTO() {
    }

    public CreditCardDTO(long id, BigDecimal balance, Long primaryOwner, Long secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate, BigDecimal penaltyFee, LocalDate creationDate) {
        this.id = id;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
    }

    public static CreditCardDTO parseFromCreditCard(CreditCard creditCard) {
        return new CreditCardDTO(creditCard.getId(),
                creditCard.getBalance().getAmount(),
                creditCard.getPrimaryOwner().getId(),
                creditCard.getSecondaryOwner().getId(),
                creditCard.getCreditLimit().getAmount(),
                creditCard.getInterestRate(),
                creditCard.getPenaltyFee().getAmount(),
                creditCard.getCreationDate());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Long getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Long secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
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

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
