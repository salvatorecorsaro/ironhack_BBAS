package com.scorsaro.bbas.model.others;

import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.enums.TransactionType;
import com.scorsaro.bbas.model.accounts.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    private Account receiverAccount;
    @ManyToOne()
    private Account senderAccount;
    @Embedded
    private Name receiverName;
    @Embedded
    private Money amount;
    private String reason;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(Account senderAccount,
                       Account receiverAccount,
                       Name receiverName,
                       Money amount,
                       String reason,
                       TransactionType transactionType) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.receiverName = receiverName;
        this.amount = amount;
        this.reason = reason;
        this.transactionType = transactionType;
        setTransactionDateTime(LocalDateTime.now());
    }

    public static Transaction parseFromTransactionDTO(Account senderAccount, Account receiverAccount, TransactionDTO transactionDTO) {
        return new Transaction(senderAccount,
                receiverAccount,
                transactionDTO.getReceiverName(), new Money(transactionDTO.getAmount()),
                transactionDTO.getReason(), TransactionType.TRANSFER
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account sender) {
        this.senderAccount = sender;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiver) {
        this.receiverAccount = receiver;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Name getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(Name receiverName) {
        this.receiverName = receiverName;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
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
}
