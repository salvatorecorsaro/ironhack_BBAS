package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ISavingController;
import com.scorsaro.bbas.dto.accounts.SavingDTO;
import com.scorsaro.bbas.service.interfaces.ISavingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saving")
public class SavingController implements ISavingController {
    @Autowired
    ISavingServices savingServices;

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<SavingDTO> findAll() {
        return savingServices.findAll();
    }

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SavingDTO create(@RequestBody SavingDTO savingDTO) {
        return savingServices.create(savingDTO);
    }
}
