package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.accounts.AccountDTO;
import com.scorsaro.bbas.dto.accounts.CheckingDTO;

public interface IAccountController {
    AccountDTO create(CheckingDTO accountDTO);
}
