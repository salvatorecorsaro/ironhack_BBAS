package com.scorsaro.bbas.model.others;

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
    private Account senderAccount;
    @ManyToOne()
    private Account receiverAccount;
    @Embedded
    private Money amount;
    private LocalDateTime dateTime;

    public Transaction() {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
