package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.users.AccountHolderDTO;

import java.util.List;

public interface IAccountHolderServices {
    List<AccountHolderDTO> getAll();

    AccountHolderDTO create(AccountHolderDTO accountHolder);


}
