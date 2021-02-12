package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.accounts.SavingDTO;
import com.scorsaro.bbas.model.accounts.Saving;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.repository.accounts.SavingRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.ISavingServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SavingServices implements ISavingServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    SavingRepository savingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public List<SavingDTO> findAll() {
        return savingRepository.findAll().stream().map(saving -> SavingDTO.parseFromSaving(saving)).collect(Collectors.toList());
    }

    @Override
    public SavingDTO create(SavingDTO savingDTO) {
        AccountHolder primaryOwner;
        AccountHolder secondaryOwner;
        long primaryOwnerId = savingDTO.getPrimaryOwner();
        long secondaryOwnerId = savingDTO.getSecondaryOwner();
        primaryOwner = getAccountHolder(primaryOwnerId, "Bad primaryOwner Id ");
        secondaryOwner = getAccountHolder(secondaryOwnerId, "Bad secondaryOwner Id ");
        Saving saving = Saving.parseFromSavingDto(primaryOwner, secondaryOwner, savingDTO);
        savingRepository.save(saving);
        return SavingDTO.parseFromSaving(saving);
    }

    private AccountHolder getAccountHolder(long primaryOwnerId, String s) {
        AccountHolder primaryOwner;
        if (primaryOwnerId >= 0) {
            LOGGER.info("Searching AccountHolder " + primaryOwnerId);
            primaryOwner = accountHolderRepository.findById(primaryOwnerId);
        } else {
            throw new IllegalArgumentException(s + primaryOwnerId);
        }
        return primaryOwner;
    }
}
