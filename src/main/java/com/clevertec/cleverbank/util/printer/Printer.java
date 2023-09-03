package com.clevertec.cleverbank.util.printer;

/**
 * Class which represents interface for printing data
 *
 * @param <T> - entity with data for printing
 */
public interface Printer<T> {

    /**
     * Method for printing data
     *
     * @param t - entity with data for printing
     */
    void print(T t);
}
