package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.accounts.CreditCardDTO;
import com.scorsaro.bbas.model.accounts.CreditCard;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.repository.accounts.CreditCardRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.ICreditCardServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardServices implements ICreditCardServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);
    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;


    @Override
    public List<CreditCardDTO> findAll() {
        return creditCardRepository.findAll().stream().map(creditCard -> CreditCardDTO.parseFromCreditCard(creditCard)).collect(Collectors.toList());
    }

    /*
        Create a CreditCard account
     */
    @Override
    public CreditCardDTO create(CreditCardDTO creditCardDTO) {
        AccountHolder primaryOwner;
        AccountHolder secondaryOwner;
        long primaryOwnerId = creditCardDTO.getPrimaryOwner();
        long secondaryOwnerId = creditCardDTO.getSecondaryOwner();
        primaryOwner = getAccountHolder(primaryOwnerId, "Bad primaryOwner Id ");
        secondaryOwner = getAccountHolder(secondaryOwnerId, "Bad secondaryOwner Id ");

        CreditCard creditCard = CreditCard.parseFromCreditCardDTO(primaryOwner, secondaryOwner, creditCardDTO);
        creditCardRepository.save(creditCard);
        return CreditCardDTO.parseFromCreditCard(creditCard);
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
