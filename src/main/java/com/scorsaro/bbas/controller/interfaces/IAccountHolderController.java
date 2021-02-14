package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;

import java.util.List;

public interface IAccountHolderController {


    List<AccountHolderDTO> getAll();

    AccountHolderDTO create(AccountHolderDTO accountHolder);

}
