package com.clevertec.cleverbank.model.bank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BankUser {

    private int id;

    private String lastname;

    private String firstname;

    private String middlename;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BankUser bankUser = (BankUser) obj;
        return id == bankUser.id && Objects.equals(lastname, bankUser.lastname) && Objects.equals(firstname,
                bankUser.firstname) && Objects.equals(middlename, bankUser.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname, middlename);
    }

    @Override
    public String toString() {
        return "BankUser{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                '}';
    }
}
