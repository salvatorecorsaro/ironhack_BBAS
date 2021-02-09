package com.scorsaro.bbas.model.accounts;

import com.scorsaro.bbas.enums.Status;
import com.scorsaro.bbas.model.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Student extends Account {
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Student() {
    }

    public Student(AccountHolder primaryOwner, String secretKey) {
        super(primaryOwner);
        this.secretKey = secretKey;
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
