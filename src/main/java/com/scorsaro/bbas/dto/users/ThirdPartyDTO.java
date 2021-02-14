package com.scorsaro.bbas.dto.users;

import com.scorsaro.bbas.model.users.ThirdParty;

import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {
    private long id;
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "HashedKey is required")
    private String hashedKey;

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(long id, String username, String hashedKey) {
        this.id = id;
        this.username = username;
        this.hashedKey = hashedKey;
    }

    public static ThirdPartyDTO parseFromThirdParty(ThirdParty thirdParty) {
        return new ThirdPartyDTO(thirdParty.getId(), thirdParty.getUsername(), thirdParty.getHashedKey());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
