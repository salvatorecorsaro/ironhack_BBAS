package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.service.interfaces.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    @Override
    public List<TransactionDTO> findAll() {
        return null;
    }
}
