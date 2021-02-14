package com.scorsaro.bbas.model.users;

import com.scorsaro.bbas.dto.users.ThirdPartyDTO;

import javax.persistence.Entity;

@Entity
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String username, String password, String hashedKey) {
        super(username, password);
        this.hashedKey = hashedKey;
    }

    public static ThirdParty parseFromThirdPartyDTO(ThirdPartyDTO thirdPartyDTO) {
        return new ThirdParty(thirdPartyDTO.getUsername(), thirdPartyDTO.getPassword(), thirdPartyDTO.getHashedKey());
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
