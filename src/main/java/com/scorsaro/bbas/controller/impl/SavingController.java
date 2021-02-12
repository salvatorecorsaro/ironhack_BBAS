package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.ISavingController;
import com.scorsaro.bbas.dto.accounts.SavingDTO;
import com.scorsaro.bbas.service.interfaces.ISavingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavingController implements ISavingController {
    @Autowired
    ISavingServices savingServices;

    @Override
    public List<SavingDTO> findAll() {
        return savingServices.findAll();
    }

    @Override
    public SavingDTO create(SavingDTO savingDTO) {
        return savingServices.create(savingDTO);
    }
}
