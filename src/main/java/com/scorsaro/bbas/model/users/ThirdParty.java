package com.scorsaro.bbas.model.users;

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

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
