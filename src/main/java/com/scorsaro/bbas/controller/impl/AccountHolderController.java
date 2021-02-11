package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.controller.interfaces.IAccountHolderController;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.service.interfaces.IAccountHolderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountHolderController implements IAccountHolderController {

    @Autowired
    IAccountHolderServices accountHolderServices;


    @Override
    @GetMapping("/account-holder")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolderDTO> getAll() {
        return accountHolderServices.getAll();
    }

    @Override
    @PostMapping("/account-holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderDTO create(@RequestBody AccountHolder accountHolder) {
        return accountHolderServices.create(accountHolder);
    }


}
