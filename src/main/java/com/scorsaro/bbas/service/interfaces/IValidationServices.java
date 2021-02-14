package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.model.users.User;

import java.time.LocalDate;

public interface IValidationServices {
    boolean validateTransaction(Transaction transaction);

    boolean validateStudentAge(LocalDate dateOfBirth);

    boolean validateAccountAreDifferent(long firstId, long secondId);

    boolean validateAccountOwnership(User user, Account senderAccount);

    boolean validateRequestName(Transaction transaction);
}
