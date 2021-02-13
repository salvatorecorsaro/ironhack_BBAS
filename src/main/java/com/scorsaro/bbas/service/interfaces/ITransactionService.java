package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.others.TransactionDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionDTO> findAll();

    TransactionDTO create(TransactionDTO transactionDTO);
}
