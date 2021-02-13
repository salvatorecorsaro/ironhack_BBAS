package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.others.Transaction;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.repository.accounts.AccountRepository;
import com.scorsaro.bbas.repository.accounts.TransactionRepository;
import com.scorsaro.bbas.service.interfaces.ITransactionService;
import com.scorsaro.bbas.service.interfaces.IValidationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServices implements ITransactionService {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    IValidationServices validationServices;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll().stream().map(transaction -> TransactionDTO.parseFromTransaction(transaction)).collect(Collectors.toList());
    }

    @Override
    public TransactionDTO create(TransactionDTO transactionDTO) {
        //id account from
        Account senderAccount = accountRepository.findAccountById(transactionDTO.getSenderAccount());
        //id account to
        Account receiverAccount = accountRepository.findAccountById(transactionDTO.getReceiverAccount());
        //username account to
        //amount to transfer
        //type
        //reason


        Transaction transaction = Transaction.parseFromTransactionDTO(senderAccount, receiverAccount, transactionDTO);
        //do verification
        if (!(validationServices.validateTransaction(transaction))) {
            throw new IllegalArgumentException("validation failed!");
        }
        transactionRepository.save(transaction);
        return TransactionDTO.parseFromTransaction(transaction);
    }

    private AccountHolder getAccountHolder(long primaryOwnerId, String s) {
        AccountHolder primaryOwner = null;
        if (primaryOwnerId >= 0) {
            LOGGER.info("Searching AccountHolder " + primaryOwnerId);
//            primaryOwner = accountHolderRepository.findById(primaryOwnerId);
        } else {
            throw new IllegalArgumentException(s + primaryOwnerId);
        }
        return primaryOwner;
    }
}
