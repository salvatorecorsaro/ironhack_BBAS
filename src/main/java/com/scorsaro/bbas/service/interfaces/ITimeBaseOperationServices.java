package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.CreditCard;
import com.scorsaro.bbas.model.accounts.Saving;

public interface ITimeBaseOperationServices {
    BalanceDTO updateCheckingBalance(Checking account);

    BalanceDTO updateSavingBalance(Saving account);

    BalanceDTO updateCreditCard(CreditCard account);
}
