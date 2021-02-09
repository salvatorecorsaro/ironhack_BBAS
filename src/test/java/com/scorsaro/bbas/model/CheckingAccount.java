package com.scorsaro.bbas.model;

import com.scorsaro.bbas.enums.Status;

import java.math.BigDecimal;

public class CheckingAccount extends Account{
    private String secretKey;
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;
    private Status status;

}
