package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.model.users.User;

public interface IBalanceController {
    BalanceDTO getOwnedAccountBalance(User user, long id);

    BalanceDTO getAllOwnedAccountBalance(User user);
}
