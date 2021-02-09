package com.scorsaro.bbas.model.users;

import com.scorsaro.bbas.model.others.Name;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class ThirdParty extends User{
    private String hashedKey;

    public ThirdParty() {
    }



    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
