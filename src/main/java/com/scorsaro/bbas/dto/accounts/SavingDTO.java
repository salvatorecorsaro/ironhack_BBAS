package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.accounts.Saving;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SavingDTO {
    private long id;
    @Valid
    @NotNull(message = "Balance is required")
    private BigDecimal balance;
    @Min(value = 1, message = "A primary owner is required to create an account")
    private Long primaryOwner;
    private Long secondaryOwner;
    private BigDecimal penaltyFee;
    private Status status;
    @NotNull(message = "SecretKey is required")
    private String secretKey;
    @DecimalMin(value = "100", message = "Minimum balance must be above 100")
    private BigDecimal minimumBalance;
    @DecimalMax(value = "0.5", message = "Interest rate must be below 0.5")
    @DecimalMin(value = "0", message = "Interest rate shouldn't be a negative value")
    private BigDecimal interestRate;
    private LocalDate creationDate;

    public SavingDTO() {
    }

    public SavingDTO(long id,
                     BigDecimal balance,
                     Long primaryOwner,
                     Long secondaryOwner,
                     BigDecimal penaltyFee,
                     Status status,
                     String secretKey,
                     BigDecimal minimumBalance,
                     BigDecimal interestRate,
                     LocalDate creationDate) {
        this.id = id;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status = status;
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
        this.creationDate = creationDate;
    }

    public static SavingDTO parseFromSaving(Saving saving) {
        return new SavingDTO(
                saving.getId(),
                saving.getBalance().getAmount(),
                saving.getPrimaryOwner().getId(),
                saving.getSecondaryOwner().getId(),
                saving.getPenaltyFee().getAmount(),
                saving.getStatus(),
                saving.getSecretKey(),
                saving.getMinimumBalance().getAmount(),
                saving.getInterestRate(),
                saving.getCreationDate()
        );
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

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}

