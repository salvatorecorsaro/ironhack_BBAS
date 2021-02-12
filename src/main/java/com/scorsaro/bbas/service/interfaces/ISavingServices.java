package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.accounts.SavingDTO;

import java.util.List;

public interface ISavingServices {
    List<SavingDTO> findAll();

    SavingDTO create(SavingDTO savingDTO);
}
