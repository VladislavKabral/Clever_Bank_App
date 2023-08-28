package com.clevertec.cleverbank.dao;

import java.util.List;

public interface DAOInterface<T> {

    List<T> findAll();

    T findById(int id);

    void save(T t);

    void update(int id, T t);

    void deleteById(int id);
}
