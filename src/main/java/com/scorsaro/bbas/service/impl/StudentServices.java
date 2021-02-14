package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.accounts.StudentDTO;
import com.scorsaro.bbas.repository.accounts.StudentRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.IStudentServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServices implements IStudentServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> StudentDTO.parseFromStudent(student)).collect(Collectors.toList());
    }
}
