package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ITransactionController;
import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.service.interfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }


}
