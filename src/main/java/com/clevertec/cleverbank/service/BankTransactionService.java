package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.dao.BankTransactionDAO;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
public class BankTransactionService {

    private final BankTransactionDAO bankTransactionDAO;

    public void sendMoneyToAccount(String inAccountName, String outAccountName, int value) throws NotEnoughMoneyException,
            SQLException {

        bankTransactionDAO.sendMoneyToAccount(inAccountName, outAccountName, value);
    }
}
