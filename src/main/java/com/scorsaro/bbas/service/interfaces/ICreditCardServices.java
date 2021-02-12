package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.accounts.CreditCardDTO;

import java.util.List;

public interface ICreditCardServices {
    List<CreditCardDTO> findAll();

    CreditCardDTO create(CreditCardDTO creditCard);
}
