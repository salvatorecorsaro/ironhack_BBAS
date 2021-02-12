package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.model.accounts.CreditCard;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCardDTO {
    private long id;
    private BigDecimal balance;
    private Long primaryOwner;
    private Long secondaryOwner;
    private BigDecimal creditLimit;
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
