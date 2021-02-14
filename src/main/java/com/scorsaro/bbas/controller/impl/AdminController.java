package com.scorsaro.bbas.controller.impl;

import com.scorsaro.bbas.controller.interfaces.IAdminController;
import com.scorsaro.bbas.dto.others.AdminRequestDTO;
import com.scorsaro.bbas.dto.users.ThirdPartyDTO;
import com.scorsaro.bbas.service.interfaces.IThirdPartyServices;
import com.scorsaro.bbas.service.interfaces.ITransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController implements IAdminController {
    @Autowired
    ITransactionServices transactionServices;

    @Autowired
    IThirdPartyServices thirdPartyServices;

    @Override
    @PostMapping("/create/third-party")
    public ThirdPartyDTO createThirdParty(@RequestBody ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyServices.createThirdParty(thirdPartyDTO);
    }

    @Override
    @PatchMapping("/modify")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AdminRequestDTO modifyAccountBalance(@RequestBody AdminRequestDTO adminRequestDTO) {
        return transactionServices.modifyAccountBalance(adminRequestDTO);
    }
}
