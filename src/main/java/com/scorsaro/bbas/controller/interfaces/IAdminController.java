package com.scorsaro.bbas.controller.interfaces;

import com.scorsaro.bbas.dto.others.AdminRequestDTO;
import com.scorsaro.bbas.dto.users.ThirdPartyDTO;

public interface IAdminController {

    ThirdPartyDTO createThirdParty(ThirdPartyDTO thirdPartyDTO);

    AdminRequestDTO modifyAccountBalance(AdminRequestDTO adminRequestDTO);
}
