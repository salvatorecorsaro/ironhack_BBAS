package com.scorsaro.bbas.controller.dto.accounts;

import com.scorsaro.bbas.controller.dto.others.TransactionDTO;

import java.util.List;
import java.util.Optional;

public class AccountDTO {
    protected Long id;
    protected Optional<String> balance;
    protected List<Optional<String>> primaryOwner;
    protected List<Optional<String>> secondaryOwner;
    protected List<Optional<TransactionDTO>> senderTransactions;
    protected List<Optional<TransactionDTO>> receiverTransactions;
    protected Optional<String> penaltyFee;
    protected Optional<String> creationDate;

    public AccountDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<String> getBalance() {
        return balance;
    }

    public void setBalance(Optional<String> balance) {
        this.balance = balance;
    }

    public List<Optional<String>> getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(List<Optional<String>> primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public List<Optional<String>> getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(List<Optional<String>> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public List<Optional<TransactionDTO>> getSenderTransactions() {
        return senderTransactions;
    }

    public void setSenderTransactions(List<Optional<TransactionDTO>> senderTransactions) {
        this.senderTransactions = senderTransactions;
    }

    public List<Optional<TransactionDTO>> getReceiverTransactions() {
        return receiverTransactions;
    }

    public void setReceiverTransactions(List<Optional<TransactionDTO>> receiverTransactions) {
        this.receiverTransactions = receiverTransactions;
    }

    public Optional<String> getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Optional<String> penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Optional<String> getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Optional<String> creationDate) {
        this.creationDate = creationDate;
    }
}
