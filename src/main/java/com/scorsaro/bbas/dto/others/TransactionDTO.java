package com.scorsaro.bbas.dto.others;

import com.scorsaro.bbas.enums.TransactionType;
import com.scorsaro.bbas.model.others.Name;
import com.scorsaro.bbas.model.others.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private long id;
    private long senderAccount;
    private long receiverAccount;
    private Name receiverName;
    private BigDecimal amount;
    private TransactionType transactionType;
    private String reason;
    private LocalDateTime localDateTime;

    public TransactionDTO() {
    }

    public TransactionDTO(long id,
                          long senderAccount,
                          long receiverAccount,
                          Name receiverName,
                          BigDecimal amount,
                          TransactionType transactionType,
                          String reason,
                          LocalDateTime localDateTime) {
        this.id = id;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.amount = amount;
        this.transactionType = transactionType;
        this.reason = reason;
        this.localDateTime = localDateTime;
    }

    public static TransactionDTO parseFromTransaction(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getSenderAccount().getId(),
                transaction.getReceiverAccount().getId(),
                transaction.getReceiverAccount().getPrimaryOwner().getName(),
                transaction.getAmount().getAmount(),
                transaction.getTransactionType(),
                transaction.getReason(),
                transaction.getTransactionDateTime()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(long senderAccount) {
        this.senderAccount = senderAccount;
    }

    public long getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(long receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Name getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(Name receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}