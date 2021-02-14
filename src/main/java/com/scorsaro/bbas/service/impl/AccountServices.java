package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.AccountRepository;
import com.scorsaro.bbas.service.interfaces.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices implements IAccountServices {
    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account findById(Long id) {
        return accountRepository.findAccountById(id);
    }

    @Override
    public Account findByIdIfOwnedByUser(User accountHolder, Long id) {
        return accountRepository.findAccountByIdIfOwnedByUser(accountHolder, id);
    }

    @Override
    public List<Account> findAllOwnedByUser(User accountHolder) {
        return accountRepository.findAllAccountsOwnedByUser(accountHolder);
    }
}
