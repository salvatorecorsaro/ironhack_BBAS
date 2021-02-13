package com.scorsaro.bbas.service.impl;

import com.scorsaro.bbas.dto.accounts.CheckingDTO;
import com.scorsaro.bbas.model.accounts.Checking;
import com.scorsaro.bbas.model.accounts.Student;
import com.scorsaro.bbas.model.users.AccountHolder;
import com.scorsaro.bbas.repository.accounts.CheckingRepository;
import com.scorsaro.bbas.repository.accounts.StudentRepository;
import com.scorsaro.bbas.repository.users.AccountHolderRepository;
import com.scorsaro.bbas.service.interfaces.ICheckingServices;
import com.scorsaro.bbas.service.interfaces.IValidationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckingServices implements ICheckingServices {
    private static final Logger LOGGER = LogManager.getLogger(CheckingServices.class);

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    IValidationServices validationServices;


    @Override
    public List<CheckingDTO> findAll() {
        return checkingRepository.findAll().stream().map(checking -> CheckingDTO.parseFromChecking(checking)).collect(Collectors.toList());
    }

    @Override
    public CheckingDTO create(CheckingDTO checkingDTO) {
        AccountHolder primaryOwner;
        AccountHolder secondaryOwner;
        long primaryOwnerId = checkingDTO.getPrimaryOwner();
        long secondaryOwnerId = checkingDTO.getSecondaryOwner();
        primaryOwner = getAccountHolder(primaryOwnerId, "Bad primaryOwner Id ");
        secondaryOwner = getAccountHolder(secondaryOwnerId, "Bad secondaryOwner Id ");
        // check if one of the owners is under 24 and in that case create a student account
        if (validationServices.validateStudentAge(primaryOwner.getDateOfBirth()) || validationServices.validateStudentAge(secondaryOwner.getDateOfBirth())) {
            Student student = Student.parseFromCheckingDTO(primaryOwner, secondaryOwner, checkingDTO);
            studentRepository.save(student);
            return CheckingDTO.parseFromStudent(student);
        } else {
            Checking checking = Checking.parseFromCheckingDTO(primaryOwner, secondaryOwner, checkingDTO);
            checkingRepository.save(checking);
            return CheckingDTO.parseFromChecking(checking);
        }
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
