package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ITransactionController;
import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements ITransactionController {
    @Autowired
    ITransactionService transactionService;

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> findAll() {
        return transactionService.findAll();
    }


}
