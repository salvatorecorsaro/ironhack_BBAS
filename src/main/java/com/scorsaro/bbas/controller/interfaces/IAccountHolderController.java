package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.model.users.AccountHolder;

import java.util.List;

public interface IAccountHolderController {


    List<AccountHolderDTO> getAll();

    AccountHolderDTO create(AccountHolder accountHolder);

}
