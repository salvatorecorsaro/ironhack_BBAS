package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.others.Name;
import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.*;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IValidationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public boolean validateTransaction(Transaction transaction) {
//        validateSufficientFunds(transaction);
        validateTimeSinceLastTransaction(transaction);
        validateDailyLimit(transaction);

        return true;
    }

    @Override
    public boolean validateStudentAge(LocalDate dateOfBirth) {
        long yearPassed = ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
        return yearPassed < 24;
    }

    @Override
    public boolean validateAccountAreDifferent(long firstId, long secondId) {
        return firstId != secondId;
    }

    @Override
    public boolean validateAccountOwnership(User user, Account senderAccount) {
        if (user.getId() == senderAccount.getPrimaryOwner().getId() || user.getId() == senderAccount.getSecondaryOwner().getId())
            return true;
        return false;
    }


    @Override
    public boolean validateRequestName(Transaction transaction) {
        Name receiverRequestName = transaction.getReceiverName();
        Name receiverExpectedName = transaction.getReceiverAccount().getPrimaryOwner().getName();
        if (!(receiverRequestName.equals(receiverExpectedName))) {
            throw new IllegalArgumentException("Name from request and account are not the same");
        }
        return true;
    }

    private void validateDailyLimit(Transaction transaction) {
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
