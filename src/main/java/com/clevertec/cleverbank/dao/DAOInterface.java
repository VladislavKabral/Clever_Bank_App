package com.clevertec.cleverbank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Class which represents interface for all DAO classes
 * @param <T> - type of entity for DAO
 * @author Vladislav Kabral
 */
public interface DAOInterface<T> {

    /**
     * Method for getting all current entities from db
     *
     * @return list of T entities
     */
    List<T> findAll();

    /**
     * Method for getting entity by id
     *
     * @param id - id of searching entity
     * @return T entity
     */
    T findById(int id);

    /**
     * Method for saving entity in database
     *
     * @param t - entity to save
     */
    void save(T t);

    /**
     * Method for updating entity in database
     *
     * @param id - entity`s id
     * @param t - entity to update
     */
    void update(int id, T t);

    /**
     * Method for deleting entity in database by id
     *
     * @param id - entity`s id
     */
    void deleteById(int id);

    /**
     * Method for setting entity`s fields from database
     *
     * @param resultSet - entity with data from database
     * @return - T entity with data from database
     * @throws SQLException
     */
    T setFieldsFromDB(ResultSet resultSet) throws SQLException;
}
