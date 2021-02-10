package com.scorsaro.bbas.controller.dto.accounts;

import java.util.Optional;

public class StudentDTO extends AccountDTO {
    private Optional<String> secretKey;
    private Optional<String> status;

    public StudentDTO() {
        super();
    }

    public Optional<String> getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(Optional<String> secretKey) {
        this.secretKey = secretKey;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }
}
