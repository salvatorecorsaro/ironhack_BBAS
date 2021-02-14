package com.scorsaro.bbas.utils;

import com.scorsaro.bbas.model.users.Admin;
import com.scorsaro.bbas.model.users.Role;
import com.scorsaro.bbas.repository.accounts.RoleRepository;
import com.scorsaro.bbas.repository.users.AdminRepository;
import com.scorsaro.bbas.service.impl.AccountHolderServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger LOGGER = LogManager.getLogger(AccountHolderServices.class);

    @Autowired
    AdminRepository adminRepository;


    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String setUpAppForTest() {
        LOGGER.info("Setting test environment");
        Admin admin = new Admin("admin", "$2a$10$L/6qhsLwEkDq037r6GdcLeprckyyM5FnW/Gjos168NGASqDkvwyD6");
//        psw
//        $2a$10$L/6qhsLwEkDq037r6GdcLeprckyyM5FnW/Gjos168NGASqDkvwyD6
        adminRepository.save(admin);
        Role role = new Role("ADMIN", admin);

        roleRepository.save(role);
        LOGGER.info("admin created");
//        adminRepository.save(admin);


        return "Test environment created";
    }
}
