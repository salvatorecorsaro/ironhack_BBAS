package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ICheckingController;
import com.scorsaro.bbas.dto.accounts.CheckingDTO;
import com.scorsaro.bbas.service.interfaces.ICheckingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checking")
public class CheckingController implements ICheckingController {
    @Autowired
    ICheckingServices checkingServices;


    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CheckingDTO> findAll() {
        return checkingServices.findAll();
    }

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public CheckingDTO create(@RequestBody CheckingDTO checkingDTO) {
        return checkingServices.create(checkingDTO);
    }
}
