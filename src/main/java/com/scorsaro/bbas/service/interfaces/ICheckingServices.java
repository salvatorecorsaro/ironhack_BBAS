package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.accounts.CheckingDTO;

import java.util.List;

public interface ICheckingServices {
    CheckingDTO create(CheckingDTO newAccountDTO);

    List<CheckingDTO> findAll();
}
