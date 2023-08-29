package com.clevertec.cleverbank.model.bank.receipt;

import java.util.Date;
import java.util.Objects;

public class BankAccountReceipt {

    private int id;

    private String type;

    private Date date;

    private int bank_id;

    private int account_id;

    private int value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
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

        BankAccountReceipt that = (BankAccountReceipt) obj;
        return id == that.id && bank_id == that.bank_id && account_id == that.account_id && value == that.value && Objects.equals(type, that.type) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, date, bank_id, account_id, value);
    }

    @Override
    public String toString() {
        return "BankAccountReceipt{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", bank_id=" + bank_id +
                ", account_id=" + account_id +
                ", value=" + value +
                '}';
    }
}
