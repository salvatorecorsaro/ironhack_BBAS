package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.IStudentController;
import com.scorsaro.bbas.dto.accounts.StudentDTO;
import com.scorsaro.bbas.service.interfaces.IStudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController implements IStudentController {
    @Autowired
    IStudentServices studentServices;

    @Override
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findAll() {
        return studentServices.findAll();
    }

    @Override
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(@RequestBody StudentDTO studentDTO) {
        return studentServices.create(studentDTO);
    }
}
