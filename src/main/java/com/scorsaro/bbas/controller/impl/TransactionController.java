package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ITransactionController;
import com.scorsaro.bbas.dto.others.TransactionDTO;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.service.interfaces.ITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements ITransactionController {
    @Autowired
    ITransactionServices transactionServices;

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> findAll() {
        return transactionServices.findAll();
    }

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO create(@AuthenticationPrincipal User user, @RequestBody TransactionDTO transactionDTO) {
        return transactionServices.transferTransaction(user, transactionDTO);
    }

    @Override
    @PostMapping("/tpu/to")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO transferToTPU(@AuthenticationPrincipal User user, @RequestBody TransactionDTO transactionDTO, @RequestParam String hashedKey) {
        return transactionServices.transferTransactionToTPU(user, transactionDTO, hashedKey);
    }

    @Override
    @PostMapping("/tpu/from")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO transferFromTPU(@AuthenticationPrincipal User user, @RequestBody TransactionDTO transactionDTO, @RequestParam String hashedKey) {
        return transactionServices.transferTransactionFromTPU(user, transactionDTO, hashedKey);
    }


}
