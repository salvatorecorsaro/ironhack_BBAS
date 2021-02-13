package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.repository.accounts.*;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IValidationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ValidationServices implements IValidationServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void validate(Transaction transaction) {
//        validateSufficientFunds(transaction);
        validateTimeSinceLastTransaction(transaction);
        validateDailyLimit(transaction);
    }

    private void validateDailyLimit(Transaction transaction) {
        //what to do the first day first transaction?
        //TODO test with more days cases
        BigDecimal highestDailyOutput = transactionRepository.findHighestDailyTransactionByCustomer(LocalDateTime.now(), transaction.getSenderAccount());
        BigDecimal todayTotalOutput = transactionRepository.findTodayTotalTransactions(LocalDateTime.now(), transaction.getSenderAccount());
        if (highestDailyOutput != null) {
            BigDecimal fraudLimitOutput = highestDailyOutput.multiply(BigDecimal.valueOf(1.5));
            if (todayTotalOutput.compareTo(BigDecimal.valueOf(100)) > 0 && todayTotalOutput.compareTo(fraudLimitOutput) > 0) {
                throw new IllegalArgumentException("Possible Fraud: Daily limit maxed");
            }
        } else if (todayTotalOutput != null && todayTotalOutput.compareTo(BigDecimal.valueOf(300)) > 0) {
            throw new IllegalArgumentException("Possible Fraud: Provisional daily limit maxed");
        } else if (transaction.getAmount().getAmount().compareTo(BigDecimal.valueOf(300)) > 0) {
            throw new IllegalArgumentException("Possible Fraud: Provisional daily limit maxed");
        }
    }

    private void validateTimeSinceLastTransaction(Transaction transaction) {
        LocalDateTime lastTransaction = transactionRepository.findLastTransaction(transaction.getSenderAccount());
        if (lastTransaction != null) {
            long secondsFromLastTransaction = ChronoUnit.SECONDS.between(lastTransaction, transaction.getTransactionDateTime());
            if (secondsFromLastTransaction < 1) {
                throw new IllegalArgumentException("FRAUD SUSPECT, SLOW DOWN");
            }
        }
    }

    private void validateSufficientFunds(Transaction transaction) {
        BigDecimal senderBalance = transaction.getSenderAccount().getBalance().getAmount();
        BigDecimal amountToTransfer = transaction.getAmount().getAmount();
        if (senderBalance.compareTo(amountToTransfer) < 0) {
            throw new IllegalArgumentException("Fund are not enough for the transaction");
        }
    }
}
