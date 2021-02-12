package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.accounts.AccountDTO;
import com.scorsaro.bbas.dto.accounts.CheckingDTO;

import java.util.List;

public interface IAccountServices {
    AccountDTO create(CheckingDTO accountDTO);

    List<AccountDTO> findAll();
}
