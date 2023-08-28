package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.BankAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAO implements DAOInterface<BankAccount> {

    private static final String URL = "jdbc:postgresql://localhost:5432/Clever_Bank_DB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "0000";

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

    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> bankAccounts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankAccount bankAccount = new BankAccount();

                bankAccount.setId(resultSet.getInt("account_id"));
                bankAccount.setName(resultSet.getString("account_name"));
                bankAccount.setBank_id(resultSet.getInt("account_bank_id"));
                bankAccount.setUser_id(resultSet.getInt("account_user_id"));
                bankAccount.setDateOfOpen(resultSet.getDate("account_date_of_open"));
                bankAccount.setValue(resultSet.getInt("account_value"));

                bankAccounts.add(bankAccount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccounts;
    }

    @Override
    public BankAccount findById(int id) {
        BankAccount bankAccount = new BankAccount();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_Account WHERE account_id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {

                bankAccount.setId(resultSet.getInt("account_id"));
                bankAccount.setName(resultSet.getString("account_name"));
                bankAccount.setBank_id(resultSet.getInt("account_bank_id"));
                bankAccount.setUser_id(resultSet.getInt("account_user_id"));
                bankAccount.setDateOfOpen(resultSet.getDate("account_date_of_open"));
                bankAccount.setValue(resultSet.getInt("account_value"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankAccount;
    }

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
