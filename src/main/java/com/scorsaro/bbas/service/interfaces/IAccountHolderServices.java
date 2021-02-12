package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;
import com.scorsaro.bbas.model.users.AccountHolder;

import java.util.List;

public interface IAccountHolderServices {
    List<AccountHolderDTO> getAll();

    AccountHolderDTO create(AccountHolder accountHolder);
}
