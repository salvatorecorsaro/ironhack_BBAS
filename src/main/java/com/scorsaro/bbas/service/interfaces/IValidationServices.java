package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.model.others.Transaction;

import java.time.LocalDate;

public interface IValidationServices {
    boolean validateTransaction(Transaction transaction);

    boolean validateStudentAge(LocalDate dateOfBirth);
}
