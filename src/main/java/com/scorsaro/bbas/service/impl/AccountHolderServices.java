package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.model.users.Role;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.RoleRepository;
import com.scorsaro.bbas.repository.accounts.UserRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IAccountHolderServices;
import com.scorsaro.bbas.service.interfaces.IAccountServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountHolderServices implements IAccountHolderServices {
    private static final Logger LOGGER = LogManager.getLogger(AccountHolderServices.class);

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    IAccountServices accountServices;


    /*
     Return a List of all the AccountHolders in the database
     */
    @Override
    public List<AccountHolderDTO> getAll() {
        LOGGER.info("Get all AccountHolder request");
        return accountHolderRepository.findAll().stream().map(accountHolder -> AccountHolderDTO.parseFromAccountHolder(accountHolder)).collect(Collectors.toList());
    }


    /*
      Create a new AccountHolder from the DTO that has been passed in the requestBody. It perform a check to assure there are not duplicated usernames
     */
    @Override
    public AccountHolderDTO create(AccountHolderDTO accountHolderDTO) {
        User foundUser = userRepository.findByUsername(accountHolderDTO.getUsername().toLowerCase());
        if (foundUser != null) {
            LOGGER.error("Error during AccountHolder creation: " + accountHolderDTO.getUsername() + " is already taken");
            throw new IllegalArgumentException("Username " + accountHolderDTO.getUsername() + " is already taken");
        }
        AccountHolder accountHolder = AccountHolder.parseFromAccountHolderDTO(accountHolderDTO);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountHolder.setPassword(passwordEncoder.encode(accountHolder.getPassword()));
        accountHolder = accountHolderRepository.save(accountHolder);
        Role role = new Role("ACCOUNT_HOLDER", accountHolder);
        roleRepository.save(role);
        accountHolderRepository.save(accountHolder);
        LOGGER.info("AccountHolder created with username: " + accountHolder.getUsername());
        return AccountHolderDTO.parseFromAccountHolder(accountHolder);
    }
}
