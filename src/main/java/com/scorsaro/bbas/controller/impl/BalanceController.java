package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.IBalanceController;
import com.scorsaro.bbas.dto.others.BalanceDTO;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.service.interfaces.IBalanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
public class BalanceController implements IBalanceController {
    @Autowired
    IBalanceServices balanceServices;


    @Override
    @GetMapping("/{id}")
    public BalanceDTO getOwnedAccountBalance(@AuthenticationPrincipal User user, @PathVariable long id) {
        return balanceServices.getOwnedAccountBalance(user, id);
    }

    @Override
    @GetMapping("/all")
    public BalanceDTO getAllOwnedAccountBalance(@AuthenticationPrincipal User user) {
        return balanceServices.getAllOwnedAccountBalance(user);
    }
}
