package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.model.users.User;
import com.scorsaro.bbas.repository.accounts.UserRepository;
import com.scorsaro.bbas.security.CustomSecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServices implements UserDetailsService {
    private static final Logger LOGGER = LogManager.getLogger(UserServices.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("[INIT] - loadUserByUsername");
        User user = userRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Invalid username/password combination.");

        LOGGER.info("[END] - loadUserByUsername");
        return new CustomSecurityUser(user);
    }
}
