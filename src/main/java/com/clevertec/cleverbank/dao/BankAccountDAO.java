package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents implementation of DAOInterface for working with bank accounts
 *
 * @author Vladislav Kabral
 */
public class BankAccountDAO implements DAOInterface<BankAccount> {

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
     * Method of getting all bank`s accounts from database
     *
     * @return list of bank`s accounts
     */
    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> bankAccounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String SQL = "SELECT * FROM Bank_Account";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankAccount bankAccount = setFieldsFromDB(resultSet);
                bankAccounts.add(bankAccount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccounts;
    }

    /**
     * Method of getting bank`s account by id
     *
     * @param id - id of searching entity
     * @return bank account
     */
    @Override
    public BankAccount findById(int id) {
        BankAccount bankAccount = new BankAccount();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account WHERE account_id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                bankAccount = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccount;
    }

    /**
     * Method of getting bank`s account from db by name
     *
     * @param name - bank`s account name
     * @return bank account
     */
    public BankAccount findByName(String name) {
        BankAccount bankAccount = new BankAccount();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account WHERE account_name = '" + name + "'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                bankAccount = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccount;
    }

    /**
     * Method for setting fields of bank`s account from database
     *
     * @param resultSet - entity with data from database
     * @return bank account
     * @throws SQLException
     */
    @Override
    public BankAccount setFieldsFromDB(ResultSet resultSet) throws SQLException {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setId(resultSet.getInt("account_id"));
        bankAccount.setName(resultSet.getString("account_name"));
        bankAccount.setBank_id(resultSet.getInt("account_bank_id"));
        bankAccount.setUser_id(resultSet.getInt("account_user_id"));
        bankAccount.setDateOfOpen(resultSet.getDate("account_date_of_open"));
        bankAccount.setValue(resultSet.getInt("account_value"));

        return bankAccount;
    }

    /**
     * Method for saving bank`s account to database
     *
     * @param bankAccount - entity to save
     */
    @Override
    public void save(BankAccount bankAccount) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Bank_Account(account_name, account_user_id, account_bank_id, account_date_of_open," +
                    " account_value) VALUES('" + bankAccount.getName() + "', '" + bankAccount.getUser_id() + "'," +
                    " '" + bankAccount.getBank_id() + "', '" + bankAccount.getDateOfOpen() + "'," +
                    " '" + bankAccount.getValue() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for updating bank`s account in database
     *
     * @param id - entity`s id
     * @param bankAccount - entity to update
     */
    @Override
    public void update(int id, BankAccount bankAccount) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE Bank_Account SET account_name = '" + bankAccount.getName() + "'," +
                    "account_user_id = '" + bankAccount.getUser_id() + "'," +
                    "account_bank_id = '" + bankAccount.getBank_id() + "'," +
                    "account_date_of_open = '" + bankAccount.getDateOfOpen() + "', " +
                    "account_value = '" + bankAccount.getValue() + "' where account_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting bank`s account in database
     *
     * @param id - entity`s id
     */
    @Override
    public void deleteById(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM Bank_Account where account_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
