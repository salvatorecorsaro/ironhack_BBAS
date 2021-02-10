package com.scorsaro.bbas.controller.dto.others;

import com.scorsaro.bbas.controller.dto.accounts.AccountDTO;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionDTO {
    private Optional<String> id;
    private Optional<AccountDTO> senderAccount;
    private Optional<AccountDTO> receiverAccount;
    private Optional<BigDecimal> amount;
    private Optional<String> dateTime;
    private Optional<String> transactionType;
    private Optional<String> reason;

    public TransactionDTO() {
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<AccountDTO> getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Optional<AccountDTO> senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Optional<AccountDTO> getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Optional<AccountDTO> receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Optional<BigDecimal> getAmount() {
        return amount;
    }

    public void setAmount(Optional<BigDecimal> amount) {
        this.amount = amount;
    }

    public Optional<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(Optional<String> dateTime) {
        this.dateTime = dateTime;
    }

    public Optional<String> getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Optional<String> transactionType) {
        this.transactionType = transactionType;
    }

    public Optional<String> getReason() {
        return reason;
    }

    public void setReason(Optional<String> reason) {
        this.reason = reason;
    }
}
