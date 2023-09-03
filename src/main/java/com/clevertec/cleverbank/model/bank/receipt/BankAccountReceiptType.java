package com.clevertec.cleverbank.model.bank.receipt;

/**
 * Enum which represents types of operations with cash
 *
 * @author Vladislav Kabral
 */
public enum BankAccountReceiptType {

    /**
     * Adding money to the account
     */
    REFILL,

    /**
     * Getting money from the account
     */
    CASH_WITHDRAWAL
}
