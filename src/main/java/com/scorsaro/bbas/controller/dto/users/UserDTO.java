package com.scorsaro.bbas.controller.dto.users;

import java.util.Optional;

public class UserDTO {
    protected Optional<String> id;
    protected Optional<String> username;
    protected Optional<String> password;

    public UserDTO() {
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getUsername() {
        return username;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }
}
