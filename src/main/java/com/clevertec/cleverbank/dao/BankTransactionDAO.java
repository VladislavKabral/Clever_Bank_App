package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.BankTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionDAO implements DAOInterface<BankTransaction> {

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
}
