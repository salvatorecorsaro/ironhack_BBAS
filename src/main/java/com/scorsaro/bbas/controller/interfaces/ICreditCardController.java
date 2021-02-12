package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.accounts.CreditCardDTO;

import java.util.List;

public interface ICreditCardController {
    List<CreditCardDTO> findAll();

    CreditCardDTO create(CreditCardDTO creditCard);
}
