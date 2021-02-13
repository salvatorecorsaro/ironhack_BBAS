package com.scorsaro.bbas.model.others;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Name() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return getFirstName().equals(name.getFirstName()) && Objects.equals(getMiddleName(), name.getMiddleName()) && getLastName().equals(name.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getMiddleName(), getLastName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
