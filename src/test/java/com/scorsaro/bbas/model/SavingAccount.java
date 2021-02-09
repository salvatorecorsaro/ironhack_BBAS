package com.scorsaro.bbas.model;

import com.scorsaro.bbas.enums.Status;

import java.math.BigDecimal;

public class SavingAccount extends Account{
    private String secretKey;
    private BigDecimal minimumBalance;
    private Status status;
    private double interestRate;
}
