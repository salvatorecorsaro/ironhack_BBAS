package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.model.users.User;

import java.util.List;

public interface ITransactionController {
    List<TransactionDTO> findAll();

    TransactionDTO create(User user, TransactionDTO transactionDTO);

    TransactionDTO transferToTPU(User user, TransactionDTO transactionDTO, String hashedKey);

    TransactionDTO transferFromTPU(User user, TransactionDTO transactionDTO, String hashedKey);
}
