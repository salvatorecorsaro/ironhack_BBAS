package com.scorsaro.bbas.service.interfaces;

import com.scorsaro.bbas.dto.accounts.StudentDTO;

import java.util.List;

public interface IStudentServices {
    List<StudentDTO> findAll();

}
