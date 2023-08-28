package com.clevertec.cleverbank.model.bank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {

    private int id;

    private String type;

    private int bankSenderId;

    private int bankRecipientId;

    private int accountSenderId;

    private int accountRecipientId;

    private Date date;

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

    public int getBankSenderId() {
        return bankSenderId;
    }

    public void setBankSenderId(int bankSenderId) {
        this.bankSenderId = bankSenderId;
    }

    public int getBankRecipientId() {
        return bankRecipientId;
    }

    public void setBankRecipientId(int bankRecipientId) {
        this.bankRecipientId = bankRecipientId;
    }

    public int getAccountSenderId() {
        return accountSenderId;
    }

    public void setAccountSenderId(int accountSenderId) {
        this.accountSenderId = accountSenderId;
    }

    public int getAccountRecipientId() {
        return accountRecipientId;
    }

    public void setAccountRecipientId(int accountRecipientId) {
        this.accountRecipientId = accountRecipientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

        BankTransaction that = (BankTransaction) obj;
        return id == that.id && bankSenderId == that.bankSenderId && bankRecipientId == that.bankRecipientId && accountSenderId == that.accountSenderId && accountRecipientId == that.accountRecipientId && value == that.value && Objects.equals(type, that.type) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, bankSenderId, bankRecipientId, accountSenderId, accountRecipientId, date, value);
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bankSenderId=" + bankSenderId +
                ", bankRecipientId=" + bankRecipientId +
                ", accountSenderId=" + accountSenderId +
                ", accountRecipientId=" + accountRecipientId +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
