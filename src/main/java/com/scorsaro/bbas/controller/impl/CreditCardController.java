package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ICreditCardController;
import com.scorsaro.bbas.dto.accounts.CreditCardDTO;
import com.scorsaro.bbas.service.interfaces.ICreditCardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit-card")
public class CreditCardController implements ICreditCardController {
    @Autowired
    ICreditCardServices creditCardService;


    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CreditCardDTO> findAll() {
        return creditCardService.findAll();
    }

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCardDTO create(@RequestBody CreditCardDTO creditCard) {
        return creditCardService.create(creditCard);
    }
}
