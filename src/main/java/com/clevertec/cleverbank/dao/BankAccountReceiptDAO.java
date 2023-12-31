package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class which represents implementation of DAOInterface for working with bank receipts
 *
 * @author Vladislav Kabral
 */
public class BankAccountReceiptDAO implements DAOInterface<BankAccountReceipt> {

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

    /*
     * Initialization of database`s connection
     */
    private static Connection connection;

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
     * Method of getting all bank`s receipts from database
     *
     * @return list of bank`s receipts with account operations
     */
    @Override
    public List<BankAccountReceipt> findAll() {
        List<BankAccountReceipt> bankAccountReceipts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account_Receipt";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankAccountReceipt bankAccountReceipt = setFieldsFromDB(resultSet);
                bankAccountReceipts.add(bankAccountReceipt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccountReceipts;
    }

    /**
     * Method for getting bank`s receipt from database by id
     *
     * @param id - id of searching entity
     * @return bank receipt
     */
    @Override
    public BankAccountReceipt findById(int id) {
        BankAccountReceipt bankAccountReceipt = new BankAccountReceipt();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account_Receipt WHERE receipt_id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                bankAccountReceipt = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccountReceipt;
    }

    /**
     * Method for saving bank`s receipt to database
     *
     * @param bankAccountReceipt - entity to save
     */
    @Override
    public void save(BankAccountReceipt bankAccountReceipt) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Bank_Account_Receipt(receipt_type, receipt_date, receipt_bank_id," +
                    "receipt_account_id, receipt_value) VALUES('" + bankAccountReceipt.getType() + "'," +
                    "'" + bankAccountReceipt.getDate() + "', '" + bankAccountReceipt.getBank_id() + "', " +
                    "'" + bankAccountReceipt.getAccount_id() + "', '" + bankAccountReceipt.getValue() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for updating bank`s receipt in database
     *
     * @param id - entity`s id
     * @param bankAccountReceipt - entity to update
     */
    @Override
    public void update(int id, BankAccountReceipt bankAccountReceipt) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE Bank_Account_Receipt SET receipt_account_id = '" + bankAccountReceipt.getAccount_id() + "'," +
                    "receipt_type = '" + bankAccountReceipt.getType() + "'," +
                    "receipt_date = '" + bankAccountReceipt.getDate() + "'," +
                    "receipt_bank_id = '" + bankAccountReceipt.getBank_id() + "'," +
                    " receipt_value = '" + bankAccountReceipt.getValue() + "' where receipt_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting bank`s receipt in database
     *
     * @param id - entity`s id
     */
    @Override
    public void deleteById(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM Bank_Account_Receipt where receipt_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for setting fields of bank`s receipt from database
     *
     * @param resultSet - entity with data from database
     * @return bank`s receipt with data from database
     * @throws SQLException
     */
    @Override
    public BankAccountReceipt setFieldsFromDB(ResultSet resultSet) throws SQLException {
        BankAccountReceipt bankAccountReceipt = new BankAccountReceipt();

        bankAccountReceipt.setId(resultSet.getInt("receipt_id"));
        bankAccountReceipt.setAccount_id(resultSet.getInt("receipt_account_id"));
        bankAccountReceipt.setBank_id(resultSet.getInt("receipt_bank_id"));
        bankAccountReceipt.setDate(resultSet.getDate("receipt_date"));
        bankAccountReceipt.setValue(resultSet.getInt("receipt_value"));
        bankAccountReceipt.setType(resultSet.getString("receipt_type"));

        return bankAccountReceipt;
    }
}
