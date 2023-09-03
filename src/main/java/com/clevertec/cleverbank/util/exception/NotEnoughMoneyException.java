package com.clevertec.cleverbank.util.exception;

/**
 * Class which represents exception type for case 'not enough money for operation'
 *
 * @author Vladislav Kabral
 */
public class NotEnoughMoneyException extends Exception {

    /**
     * Constructor of class
     *
     * @param message - message of exception
     */
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
