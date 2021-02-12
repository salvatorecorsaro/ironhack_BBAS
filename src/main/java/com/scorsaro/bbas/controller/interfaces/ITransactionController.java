package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.others.TransactionDTO;

import java.util.List;

public interface ITransactionController {
    List<TransactionDTO> findAll();
}
