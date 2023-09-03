package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.BankUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents implementation of DAOInterface for working with bank users
 *
 * @author Vladislav Kabral
 */
public class BankUserDAO implements DAOInterface<BankUser> {

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
     * Method of getting all bank`s users from database
     *
     * @return list of bank users
     */
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

    /**
     * Method for getting bank`s user from database by id
     *
     * @param id - id of searching entity
     * @return bank user
     */
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

    /**
     * Method for saving bank`s user to database
     *
     * @param bankUser - entity to save
     */
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

    /**
     * Method for updating bank`s user in database
     *
     * @param id - entity`s id
     * @param bankUser - entity to update
     */
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

    /**
     * Method for deleting bank`s user from database by id
     *
     * @param id - entity`s id
     */
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

    /**
     * Method for setting fields of bank`s user form database
     *
     * @param resultSet - entity with data from database
     * @return bank user with data from database
     * @throws SQLException
     */
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
