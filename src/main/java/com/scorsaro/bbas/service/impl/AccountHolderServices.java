package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.UserRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IAccountHolderServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountHolderServices implements IAccountHolderServices {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(AccountHolderServices.class);

    @Override
    public List<AccountHolderDTO> getAll() {
        LOGGER.info("Get all AccountHolder request");
        return accountHolderRepository.findAll().stream().map(accountHolder -> AccountHolderDTO.parseFromAccountHolder(accountHolder)).collect(Collectors.toList());
    }

    @Override
    public AccountHolderDTO create(AccountHolder accountHolder) {
        User foundUser = userRepository.findByUsername(accountHolder.getUsername());
        if (foundUser != null) {
            LOGGER.error("Error during AccountHolder creation: " + accountHolder.getUsername() + " is already taken");
            throw new IllegalArgumentException("Username " + accountHolder.getUsername() + " is already taken");
        }

        accountHolderRepository.save(accountHolder);
        LOGGER.info("AccountHolder created with username: " + accountHolder.getUsername());

        return AccountHolderDTO.parseFromAccountHolder(accountHolder);
    }


}
