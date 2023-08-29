package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.Bank;
import com.clevertec.cleverbank.model.bank.BankUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankUserDAO implements DAOInterface<BankUser> {

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
    public List<BankUser> findAll() {
        List<BankUser> bankUsers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_User";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                BankUser bankUser = setFieldsFromDB(resultSet);
                bankUsers.add(bankUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankUsers;
    }

    @Override
    public BankUser findById(int id) {
        BankUser bankUser = new BankUser();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank_User WHERE user_id = '" + id + "'";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                bankUser = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bankUser;
    }

    @Override
    public void save(BankUser bankUser) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Bank_User(user_lastname, user_firstname, user_middlename)" +
                    " VALUES('" + bankUser.getLastname() + "', '" + bankUser.getFirstname() + "'," +
                    " '" + bankUser.getMiddlename() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, BankUser bankUser) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE Bank_User SET user_lastname = '" + bankUser.getLastname() + "'," +
                    " user_firstname = '" + bankUser.getFirstname() + "'," +
                    " user_middlename = '" + bankUser.getMiddlename() + "'" +
                    "  where user_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM Bank_User where user_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BankUser setFieldsFromDB(ResultSet resultSet) throws SQLException {
        BankUser bankUser = new BankUser();

        bankUser.setId(resultSet.getInt("user_id"));
        bankUser.setLastname(resultSet.getString("user_lastname"));
        bankUser.setFirstname(resultSet.getString("user_firstname"));
        bankUser.setMiddlename(resultSet.getString("user_middlename"));

        return bankUser;
    }
}
