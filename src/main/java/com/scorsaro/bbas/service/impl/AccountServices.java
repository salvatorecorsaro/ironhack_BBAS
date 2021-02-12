package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.accounts.AccountDTO;
import com.scorsaro.bbas.dto.accounts.CheckingDTO;
import com.scorsaro.bbas.service.interfaces.IAccountServices;
import com.scorsaro.bbas.service.interfaces.ICheckingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices implements IAccountServices {
    @Autowired
    ICheckingServices checkingServices;

    @Override
    public AccountDTO create(CheckingDTO accountDTO) {
        AccountDTO retAccount = null;


        return retAccount;
    }

    @Override
    public List<AccountDTO> findAll() {
        return null;
    }
}
