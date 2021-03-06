package com.scorsaro.bbas.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.scorsaro.bbas.model.others.Address;
import com.scorsaro.bbas.model.others.Name;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.model.users.Role;
import com.scorsaro.bbas.repository.accounts.RoleRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountHolderControllerTest {


    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AccountHolder accountHolder = new AccountHolder("pepito", passwordEncoder.encode("pepito"),
                new Name("Luca", "Lulo", "Dominguez"), LocalDate.of(1999, 12, 12),
                new Address("Madrid", "España", "Ancora 6", "28045"));

        Role role = new Role("ACCOUNT_HOLDER", accountHolder);
        accountHolderRepository.save(accountHolder);
        roleRepository.save(role);
    }

    @AfterEach
    void tearDown() {

        accountHolderRepository.deleteAll();
        roleRepository.deleteAll();

    }

    @Test
    void getAll() throws Exception {
        MvcResult result = mockMvc.perform(get("/account-holder")
                .with(user("admin").roles("ADMIN"))).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Luca"));
    }

    @Test
    void getAll_fail() throws Exception {
        MvcResult result = mockMvc.perform(get("/account-holder")
                .with(user("admin").roles("ADMIN"))).andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("Renato"));
    }

    @Test
    void create() {
    }
}