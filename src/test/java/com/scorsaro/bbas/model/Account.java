package com.scorsaro.bbas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {
    private long id;
    private BigDecimal balance;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;
    private BigDecimal penaltyFee;
    private LocalDate creationDate;
}
