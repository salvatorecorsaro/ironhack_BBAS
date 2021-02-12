package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.dto.accounts.StudentDTO;
import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
public class Student extends Account {
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Student() {
    }

    public Student(AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Status status) {
        super(primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.status = status;
    }

    public static Student parseFromStudentDTO(AccountHolder primaryOwner, AccountHolder secondaryOwner, StudentDTO studentDTO) {
        return new Student(primaryOwner, secondaryOwner, studentDTO.getSecretKey(), studentDTO.getStatus());
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
