package com.clevertec.cleverbank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {

    List<T> findAll();

    T findById(int id);

    void save(T t);

    void update(int id, T t);

    void deleteById(int id);

    T setFieldsFromDB(ResultSet resultSet) throws SQLException;
}
