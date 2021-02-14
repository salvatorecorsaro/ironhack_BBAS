package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.users.ThirdPartyDTO;
import com.scorsaro.bbas.model.users.Role;
import com.scorsaro.bbas.model.users.ThirdParty;
import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.RoleRepository;
import com.scorsaro.bbas.repository.accounts.UserRepository;
import com.scorsaro.bbas.repository.users.ThirdPartyRepository;
import com.scorsaro.bbas.service.interfaces.IThirdPartyServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyServices implements IThirdPartyServices {
    private static final Logger LOGGER = LogManager.getLogger(AccountHolderServices.class);
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    /*
    Create a ThirdPartyUser
     */
    @Override
    public ThirdPartyDTO createThirdParty(ThirdPartyDTO thirdPartyDTO) {
        User foundUser = userRepository.findByUsername(thirdPartyDTO.getUsername());
        if (foundUser != null) {
            LOGGER.error("Error during AccountHolder creation: " + thirdPartyDTO.getUsername() + " is already taken");
            throw new IllegalArgumentException("Username " + thirdPartyDTO.getUsername() + " is already taken");
        }
        ThirdParty thirdParty = ThirdParty.parseFromThirdPartyDTO(thirdPartyDTO);
        thirdPartyRepository.save(thirdParty);

        Role role = new Role("THIRD_PARTY", thirdParty);
        roleRepository.save(role);
        LOGGER.info("TPU created with username: " + thirdParty.getUsername());
        return ThirdPartyDTO.parseFromThirdParty(thirdParty);
    }
}
