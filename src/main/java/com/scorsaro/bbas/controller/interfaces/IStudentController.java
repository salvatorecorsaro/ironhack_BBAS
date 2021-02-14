package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.accounts.StudentDTO;

import java.util.List;

public interface IStudentController {
    List<StudentDTO> findAll();

}
