package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.model.accounts.Account;
import com.scorsaro.bbas.model.users.User;

import java.util.List;

public interface IAccountServices {

    Account findById(Long id);

    Account findByIdIfOwnedByUser(User accountHolder, Long id);

    List<Account> findAllOwnedByUser(User accountHolder);
}
