package com.clevertec.cleverbank.dao;

import com.clevertec.cleverbank.model.bank.Bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which represents implementation of DAOInterface for working with banks
 *
 * @author Vladislav Kabral
 */
public class BankDAO implements DAOInterface<Bank> {

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
     * Method for getting all banks from database
     *
     * @return list of banks
     */
    @Override
    public List<Bank> findAll() {
        List<Bank> banks = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Bank bank = setFieldsFromDB(resultSet);
                banks.add(bank);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return banks;
    }

    /**
     * Method of getting bank form database by id
     *
     * @param id - id of searching entity
     * @return bank
     */
    @Override
    public Bank findById(int id) {

        Bank bank = new Bank();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Bank WHERE bank_id = " + id;
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                bank = setFieldsFromDB(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bank;
    }

    /**
     * Method for saving bank to database
     *
     * @param bank - entity to save
     */
    @Override
    public void save(Bank bank) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Bank(bank_name) VALUES('" + bank.getName() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for updating bank in database
     *
     * @param id - entity`s id
     * @param bank - entity to update
     */
    @Override
    public void update(int id, Bank bank) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "UPDATE Bank SET bank_name = '" + bank.getName() + "' where bank_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting bank from database by id
     *
     * @param id - entity`s id
     */
    @Override
    public void deleteById(int id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM Bank where bank_id = '" + id + "'";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for setting fields of bank from database
     *
     * @param resultSet - entity with data from database
     * @return bank
     * @throws SQLException
     */
    @Override
    public Bank setFieldsFromDB(ResultSet resultSet) throws SQLException {
        Bank bank = new Bank();

        bank.setId(resultSet.getInt("bank_id"));
        bank.setName(resultSet.getString("bank_name"));

        return bank;
    }
}
