package com.scorsaro.bbas.controller.dto.users;

import java.util.Optional;

public class ThirdPartyDTO extends UserDTO {
    private Optional<String> hashedKey;

    public ThirdPartyDTO() {
    }

    public Optional<String> getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(Optional<String> hashedKey) {
        this.hashedKey = hashedKey;
    }
}
