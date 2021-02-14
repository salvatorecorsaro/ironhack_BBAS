package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.others.AdminRequestDTO;
import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.CreditCard;
import com.scorsaro.bbas.model.accounts.Saving;
import com.scorsaro.bbas.model.users.User;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionServices {
    List<TransactionDTO> findAll();

    TransactionDTO transferTransaction(User user, TransactionDTO transactionDTO);

    AdminRequestDTO modifyAccountBalance(AdminRequestDTO adminRequestDTO);

    void createMonthlyMaintenanceFee(Checking account, BigDecimal monthlyMaintenanceFeeToPay);

    void createInterestTransaction(Saving account, BigDecimal interestToAdd);

    void createInterestTransaction(CreditCard account, BigDecimal interestToAdd);

    TransactionDTO transferTransactionToTPU(User user, TransactionDTO transactionDTO, String hashedKey);

    TransactionDTO transferTransactionFromTPU(User user, TransactionDTO transactionDTO, String hashedKey);
}
