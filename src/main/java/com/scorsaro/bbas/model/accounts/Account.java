package com.scorsaro.bbas.model.accounts;


import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.model.others.Money;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    @Embedded
    private Money balance;

    @ManyToOne
    private AccountHolder primaryOwner;
    @ManyToOne
    private AccountHolder secondaryOwner;

    @OneToMany (mappedBy = "senderAccount")
    private List<Transaction> senderTransactions = new ArrayList<>();
    @OneToMany (mappedBy = "receiverAccount")
    private List<Transaction> receiverTransaction = new ArrayList<>();

    private BigDecimal penaltyFee;
    private LocalDate creationDate;

    public Account() {
    }

    public Account(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
