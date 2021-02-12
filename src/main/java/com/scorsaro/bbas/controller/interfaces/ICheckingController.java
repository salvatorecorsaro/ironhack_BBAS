package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.accounts.CheckingDTO;

import java.util.List;

public interface ICheckingController {
    List<CheckingDTO> findAll();

    CheckingDTO create(CheckingDTO checkingDTO);
}
