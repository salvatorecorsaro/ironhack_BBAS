package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.accounts.SavingDTO;

import java.util.List;

public interface ISavingController {
    List<SavingDTO> findAll();

    SavingDTO create(SavingDTO savingDTO);
}
