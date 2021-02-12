package com.scorsaro.bbas.dto.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.accounts.Student;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StudentDTO {
    private long id;
    private BigDecimal balance;
    private Long primaryOwner;
    private Long secondaryOwner;
    private BigDecimal penaltyFee;
    private Status status;
    private String secretKey;
    private LocalDate creationDate;

    public StudentDTO() {
    }

    public StudentDTO(long id,
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

    public static StudentDTO parseFromStudent(Student student) {
        return new StudentDTO(
                student.getId(),
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
