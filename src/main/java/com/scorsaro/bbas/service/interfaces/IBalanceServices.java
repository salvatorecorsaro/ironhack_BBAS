package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.model.users.User;

public interface IBalanceServices {
    BalanceDTO getOwnedAccountBalance(User user, long id);

    BalanceDTO getAllOwnedAccountBalance(User user);
}
