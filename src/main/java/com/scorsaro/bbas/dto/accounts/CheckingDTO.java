package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.Student;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckingDTO {
    private long id;
    private BigDecimal balance;
    private Long primaryOwner;
    private Long secondaryOwner;
    private BigDecimal penaltyFee;
    private Status status;
    private String secretKey;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private LocalDate creationDate;

    public CheckingDTO() {
    }

    public CheckingDTO(long id,
                       BigDecimal balance,
                       Long primaryOwner,
                       Long secondaryOwner,
                       BigDecimal penaltyFee,
                       Status status,
                       String secretKey,
                       BigDecimal minimumBalance,
                       BigDecimal monthlyMaintenanceFee,
                       LocalDate creationDate) {
        this.id = id;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status = status;
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.creationDate = creationDate;
    }

    public CheckingDTO(long id,
                       BigDecimal balance,
                       Long primaryOwner,
                       Long secondaryOwner,
                       BigDecimal penaltyFee,
                       Status status,
                       String secretKey,
                       LocalDate creationDate) {
        this.id = id;
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status = status;
        this.secretKey = secretKey;
        this.creationDate = creationDate;
    }

    public static CheckingDTO parseFromChecking(Checking checking) {
        return new CheckingDTO(checking.getId(),
                checking.getBalance().getAmount(),
                checking.getPrimaryOwner().getId(),
                checking.getSecondaryOwner().getId(),
                checking.getPenaltyFee().getAmount(),
                checking.getStatus(),
                checking.getSecretKey(),
                checking.getMinimumBalance().getAmount(),
                checking.getMonthlyMaintenanceFee().getAmount(),
                checking.getCreationDate()
        );
    }


    public static CheckingDTO parseFromStudent(Student student) {
        return new CheckingDTO(student.getId(),
                student.getBalance().getAmount(),
                student.getPrimaryOwner().getId(),
                student.getSecondaryOwner().getId(),
                student.getPenaltyFee().getAmount(),
                student.getStatus(),
                student.getSecretKey(),
                student.getCreationDate()
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

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}