package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.BankTransaction;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class which represents implementation of DAOInterface for working with bank transactions
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
public class BankTransactionDAO implements DAOInterface<BankTransaction> {

    /**
     * Constant which represents URL for connection to database
     */
    private static final String URL = "jdbc:postgresql://localhost:5432/Clever_Bank_DB";

    /**
     * Constant which represents username for connection to database
     */
    private static final String USERNAME = "postgres";

    /**
     * Constant which represents password for connection to database
     */
    private static final String PASSWORD = "0000";

    /**
     * Constant which represents name of transaction type
     */
    private static final String TRANSFER_TO_ACCOUNT_TRANSACTION_TYPE = "Transfer to account";

    /**
     * Field which represents entity for working with bank`s accounts
     */
    private final BankAccountDAO bankAccountDAO;

    /**
     * Field which represents connection to database
     */
    private static Connection connection;

    /*
     * Initialization of database`s connection
     */
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method of getting all bank`s transactions from database
     *
     * @return list of bank transactions
     */
    @Override
    public List<BankTransaction> findAll() {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Transaction";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankTransaction bankTransaction = setFieldsFromDB(resultSet);
                bankTransactions.add(bankTransaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankTransactions;
    }

    /**
     * Method of getting bank`s transaction from database by id
     *
     * @param id - id of searching entity
     * @return bank transaction
     */
    @Override
    public BankTransaction findById(int id) {
        BankTransaction bankTransaction = new BankTransaction();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Transaction WHERE transaction_id = '" + id +"'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                bankTransaction = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankTransaction;
    }

    /**
     * Method for saving bank`s transaction to database
     *
     * @param bankTransaction - entity to save
     */
    @Override
    public void save(BankTransaction bankTransaction) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Bank_Transaction(transaction_type, transaction_bank_sender_id," +
                    " transaction_bank_recipient_id, transaction_account_sender_id, transaction_account_recipient_id," +
                    " transaction_date, transaction_value) VALUES('" + bankTransaction.getType() + "', " +
                    "'" + bankTransaction.getBankSenderId() + "', '" + bankTransaction.getBankRecipientId() + "', " +
                    "'" + bankTransaction.getAccountSenderId() + "', '" + bankTransaction.getAccountRecipientId() + "'," +
                    "'" + bankTransaction.getDate() + "', '" + bankTransaction.getValue() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for updating bank`s transaction in database
     *
     * @param id - entity`s id
     * @param bankTransaction - entity to update
     */
    @Override
    public void update(int id, BankTransaction bankTransaction) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE Bank_Transaction SET transaction_type = '" + bankTransaction.getType() + "', " +
                    "transaction_bank_sender_id = '" + bankTransaction.getBankSenderId() + "', " +
                    "transaction_bank_recipient_id = '" + bankTransaction.getBankRecipientId() + "'," +
                    "transaction_account_sender_id = '" + bankTransaction.getAccountSenderId() + "'," +
                    "transaction_account_recipient_id = '" + bankTransaction.getAccountRecipientId() + "'," +
                    "transaction_date = '" + bankTransaction.getDate() + "', " +
                    "transaction_value = '" + bankTransaction.getValue() + "' where transaction_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting bank`s transaction from database
     *
     * @param id - entity`s id
     */
    @Override
    public void deleteById(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM Bank_Transaction where transaction_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for setting fields of bank`s transaction from database
     *
     * @param resultSet - entity with data from database
     * @return bank transaction with data from database
     * @throws SQLException
     */
    @Override
    public BankTransaction setFieldsFromDB(ResultSet resultSet) throws SQLException {
        BankTransaction bankTransaction = new BankTransaction();

        bankTransaction.setId(resultSet.getInt("transaction_id"));
        bankTransaction.setType(resultSet.getString("transaction_type"));
        bankTransaction.setBankSenderId(resultSet.getInt("transaction_bank_sender_id"));
        bankTransaction.setBankRecipientId(resultSet.getInt("transaction_bank_recipient_id"));
        bankTransaction.setAccountSenderId(resultSet.getInt("transaction_account_sender_id"));
        bankTransaction.setAccountRecipientId(resultSet.getInt("transaction_account_recipient_id"));
        bankTransaction.setDate(resultSet.getDate("transaction_date"));
        bankTransaction.setValue(resultSet.getInt("transaction_value"));

        return bankTransaction;
    }


    /**
     * Method for sending money from one account to another
     *
     * @param inAccountName - name of account-sender
     * @param outAccountName - name of account-recipient
     * @param value - value of transaction
     * @throws SQLException
     * @throws NotEnoughMoneyException - throws, if account-sender has not enough money for transfer
     */
    public void sendMoneyToAccount(String inAccountName, String outAccountName, int value) throws SQLException,
            NotEnoughMoneyException {

        BankTransaction bankTransaction = new BankTransaction();

        BankAccount inAccount = bankAccountDAO.findByName(inAccountName);
        if (inAccount.getValue() >= value) {
            inAccount.setValue(inAccount.getValue() - value);
        } else {
            throw new NotEnoughMoneyException("Not enough money in the account");
        }

        BankAccount outAccount = bankAccountDAO.findByName(outAccountName);
        outAccount.setValue(outAccount.getValue() + value);

        try {
            connection.setAutoCommit(false);

            bankAccountDAO.update(inAccount.getId(), inAccount);
            bankAccountDAO.update(outAccount.getId(), outAccount);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }

        bankTransaction.setAccountSenderId(inAccount.getId());
        bankTransaction.setAccountRecipientId(outAccount.getId());
        bankTransaction.setBankSenderId(inAccount.getBank_id());
        bankTransaction.setBankRecipientId(outAccount.getBank_id());
        bankTransaction.setDate(new Date());
        bankTransaction.setType(TRANSFER_TO_ACCOUNT_TRANSACTION_TYPE);
        bankTransaction.setValue(value);

        save(bankTransaction);
    }
}
