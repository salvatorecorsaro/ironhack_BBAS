package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.IAccountController;
import com.scorsaro.bbas.dto.accounts.AccountDTO;
import com.scorsaro.bbas.dto.accounts.CheckingDTO;
import com.scorsaro.bbas.service.interfaces.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    IAccountServices accountServices;

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> findAll() {
        return accountServices.findAll();
    }

    @Override
    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody CheckingDTO accountDTO) {
        return accountServices.create(accountDTO);
    }
}
