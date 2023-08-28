package com.clevertec.cleverbank.model.bank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    private int id;

    private String name;

    private int user_id;

    private int bank_id;

    private Date dateOfOpen;

    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public Date getDateOfOpen() {
        return dateOfOpen;
    }

    public void setDateOfOpen(Date dateOfOpen) {
        this.dateOfOpen = dateOfOpen;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BankAccount that = (BankAccount) obj;
        return id == that.id && user_id == that.user_id && bank_id == that.bank_id && value == that.value &&
                Objects.equals(name, that.name) && Objects.equals(dateOfOpen, that.dateOfOpen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, user_id, bank_id, dateOfOpen, value);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user_id=" + user_id +
                ", bank_id=" + bank_id +
                ", dateOfOpen=" + dateOfOpen +
                ", value=" + value +
                '}';
    }
}
