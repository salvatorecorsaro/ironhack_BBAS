package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.enums.TransactionType;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.CreditCard;
import com.scorsaro.bbas.model.accounts.Saving;
import com.scorsaro.bbas.model.others.Money;
import com.scorsaro.bbas.repository.accounts.TransactionRepository;
import com.scorsaro.bbas.service.interfaces.ITimeBaseOperationServices;
import com.scorsaro.bbas.service.interfaces.ITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TimeBasedOperationsServices implements ITimeBaseOperationServices {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ITransactionServices transactionServices;

    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    @Override
    public BalanceDTO updateCheckingBalance(Checking account) {
        Money accountBalance = account.getBalance();
        BigDecimal monthlyMaintenanceFeeToPay = BigDecimal.ZERO;
        long monthsPassed;
        LocalDateTime lastMMF = transactionRepository.findLastTransactionOfSpecificType(account, TransactionType.MONTHLY_MAINTENANCE_FEE);
        if (lastMMF != null) monthsPassed = ChronoUnit.MONTHS.between(lastMMF, LocalDate.now());
        else monthsPassed = ChronoUnit.MONTHS.between(account.getCreationDate(), LocalDate.now());
        return applyMonthlyMaintenanceFee(account, accountBalance, monthlyMaintenanceFeeToPay, monthsPassed);
    }

    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    @Override
    public BalanceDTO updateSavingBalance(Saving account) {
        Money accountBalance = account.getBalance();
        BigDecimal interestToAdd = BigDecimal.ZERO;
        long yearsPassed;
        LocalDateTime lastInterest = transactionRepository.findLastTransactionOfSpecificType(account, TransactionType.INTEREST);
        if (lastInterest != null) yearsPassed = ChronoUnit.YEARS.between(lastInterest, LocalDate.now());
        else yearsPassed = ChronoUnit.YEARS.between(account.getCreationDate(), LocalDate.now());
        return applyYearInterest(account, accountBalance, interestToAdd, yearsPassed);
    }

    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    @Override
    public BalanceDTO updateCreditCard(CreditCard account) {
        Money accountBalance = account.getBalance();
        BigDecimal interestToAdd = BigDecimal.ZERO;
        long monthsPassed;
        LocalDateTime lastInterest = transactionRepository.findLastTransactionOfSpecificType(account, TransactionType.INTEREST);
        if (lastInterest != null) monthsPassed = ChronoUnit.MONTHS.between(lastInterest, LocalDate.now());
        else monthsPassed = ChronoUnit.MONTHS.between(account.getCreationDate(), LocalDate.now());
        return applyMonthlyInterest(account, accountBalance, interestToAdd, monthsPassed);
    }

    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    private BalanceDTO applyMonthlyInterest(CreditCard account, Money accountBalance, BigDecimal interestToAdd, long monthsPassed) {
        if (monthsPassed > 0) {
            for (long i = 0; i < monthsPassed; i++) {
                interestToAdd = interestToAdd.add(accountBalance.getAmount().multiply((account.getInterestRate().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP))));
            }
            transactionServices.createInterestTransaction(account, interestToAdd);
        }
        return new BalanceDTO(accountBalance.getAmount().add(interestToAdd));
    }


    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    private BalanceDTO applyYearInterest(Saving account, Money accountBalance, BigDecimal interestToAdd, long yearsPassed) {
        if (yearsPassed > 0) {
            for (long i = 0; i < yearsPassed; i++) {
                interestToAdd = interestToAdd.add(accountBalance.getAmount().multiply(account.getInterestRate()));
            }
            transactionServices.createInterestTransaction(account, interestToAdd);
        }
        return new BalanceDTO(accountBalance.getAmount().add(interestToAdd));
    }

    /*
    Return an updated balance (timeBasedOperation like monthly fee and interest are applied)
     */
    private BalanceDTO applyMonthlyMaintenanceFee(Checking account, Money accountBalance, BigDecimal monthlyMaintenanceFeeToPay, long monthsPassed) {
        if (monthsPassed > 0) {
            for (long i = 0; i < monthsPassed; i++) {
                monthlyMaintenanceFeeToPay = monthlyMaintenanceFeeToPay.add(account.getMonthlyMaintenanceFee().getAmount());
            }
            transactionServices.createMonthlyMaintenanceFee(account, monthlyMaintenanceFeeToPay);
        }
        return new BalanceDTO(accountBalance.getAmount().subtract(monthlyMaintenanceFeeToPay));
    }
}
