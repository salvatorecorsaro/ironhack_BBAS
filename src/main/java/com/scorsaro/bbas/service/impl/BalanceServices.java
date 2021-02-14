package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.model.accounts.*;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.UserRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IAccountServices;
import com.scorsaro.bbas.service.interfaces.IBalanceServices;
import com.scorsaro.bbas.service.interfaces.ITimeBaseOperationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceServices implements IBalanceServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    IAccountServices accountServices;

    @Autowired
    ITimeBaseOperationServices timeBaseOperationServices;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    /*
    Return the updated balance of the request account with all the temporal operation applied
     */
    @Override
    public BalanceDTO getOwnedAccountBalance(User user, long id) {

        User foundUser = userRepository.findByUsername(user.getUsername());
        Account account = accountServices.findByIdIfOwnedByUser(foundUser, id);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (account instanceof Checking) {
            return timeBaseOperationServices.updateCheckingBalance((Checking) account);
        } else if (account instanceof Student) {
            return new BalanceDTO(account.getBalance().getAmount());
        } else if (account instanceof Saving) {
            return timeBaseOperationServices.updateSavingBalance((Saving) account);
        } else if (account instanceof CreditCard) {
            return timeBaseOperationServices.updateCreditCard((CreditCard) account);
        }
        return new BalanceDTO();
    }

    /*
    Return the updated balance of the sum of all the account of the user with all the temporal operation applied
     */
    @Override
    public BalanceDTO getAllOwnedAccountBalance(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        List<Account> accountList = accountServices.findAllOwnedByUser(foundUser);
        BalanceDTO balanceDTO = new BalanceDTO(BigDecimal.ZERO);
        for (Account account : accountList) {
            if (account instanceof Checking) {
                balanceDTO.setBalance(balanceDTO.getBalance().add(timeBaseOperationServices.updateCheckingBalance((Checking) account).getBalance()));
            } else if (account instanceof Student) {
                balanceDTO.setBalance(balanceDTO.getBalance().add(account.getBalance().getAmount()));
            } else if (account instanceof Saving) {
                balanceDTO.setBalance(balanceDTO.getBalance().add(timeBaseOperationServices.updateSavingBalance((Saving) account).getBalance()));
            } else if (account instanceof CreditCard) {
                balanceDTO.setBalance(balanceDTO.getBalance().add(timeBaseOperationServices.updateCreditCard((CreditCard) account).getBalance()));
            }
        }
        return balanceDTO;
    }
}
