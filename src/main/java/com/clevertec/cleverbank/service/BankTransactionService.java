package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.dao.BankTransactionDAO;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;

import java.sql.SQLException;

/**
 * Class which represents service for working with bank`s transactions
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
public class BankTransactionService {

    /**
     * Field which represent entity for working with bank`s transactions
     */
    private final BankTransactionDAO bankTransactionDAO;

    /**
     * Method for sending money from one account to another
     *
     * @param inAccountName - name of account-sender
     * @param outAccountName - name of account-recipient
     * @param value - value of sending
     * @throws NotEnoughMoneyException - throws, if account-sender has not enough money for transfer
     * @throws SQLException
     */
    public void sendMoneyToAccount(String inAccountName, String outAccountName, int value) throws NotEnoughMoneyException,
            SQLException {

        bankTransactionDAO.sendMoneyToAccount(inAccountName, outAccountName, value);
    }
}
