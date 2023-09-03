package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.dao.*;
import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceiptType;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import com.clevertec.cleverbank.util.printer.BankAccountReceiptPrinter;
import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * Class which represents service for working with bank`s accounts
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
public class BankAccountService {

    /**
     * Field which represents entity for working with bank`s accounts
     */
    private final BankAccountDAO accountDAO;

    /**
     * Field which represents entity for working with bank`s receipts
     */
    private final BankAccountReceiptDAO bankAccountReceiptDAO;

    /**
     * Field which represents entity for printing receipts
     */
    private final BankAccountReceiptPrinter bankAccountReceiptPrinter;

    /**
     * Method for adding money to the account
     *
     * @param accountName - name of account
     * @param value - value for adding to the account
     */
    public void addMoneyToAccount(String accountName, int value) {
        BankAccount bankAccount = accountDAO.findByName(accountName);
        bankAccount.setValue(bankAccount.getValue() + value);
        accountDAO.update(bankAccount.getId(), bankAccount);

        BankAccountReceipt bankAccountReceipt = generateReceipt(bankAccount,value, BankAccountReceiptType.REFILL);

        bankAccountReceiptDAO.save(bankAccountReceipt);
        bankAccountReceiptPrinter.print(bankAccountReceipt);
    }

    /**
     * Method for getting money from the account
     *
     * @param accountName - name of account
     * @param value - value for getting from account
     * @throws NotEnoughMoneyException - throws, if bank`s account has not enough money for operation
     */
    public void getMoneyFromAccount(String accountName, int value) throws NotEnoughMoneyException {
        BankAccount bankAccount = accountDAO.findByName(accountName);

        if (bankAccount.getValue() >= value) {
            bankAccount.setValue(bankAccount.getValue() - value);
            accountDAO.update(bankAccount.getId(), bankAccount);

            BankAccountReceipt bankAccountReceipt = generateReceipt(bankAccount,value, BankAccountReceiptType.CASH_WITHDRAWAL);

            bankAccountReceiptDAO.save(bankAccountReceipt);
            bankAccountReceiptPrinter.print(bankAccountReceipt);
        } else {
            throw new NotEnoughMoneyException("Not enough money in the account");
        }
    }

    /**
     * Method for generation receipt for operation
     *
     * @param bankAccount - bank account
     * @param value - value of operation
     * @param type - type of operation
     * @return bank receipt with data about operation
     */
    private BankAccountReceipt generateReceipt(BankAccount bankAccount, int value, BankAccountReceiptType type) {
        BankAccountReceipt bankAccountReceipt = new BankAccountReceipt();

        bankAccountReceipt.setDate(new Date());
        bankAccountReceipt.setType(type.toString());
        bankAccountReceipt.setAccount_id(bankAccount.getId());
        bankAccountReceipt.setBank_id(bankAccount.getBank_id());
        bankAccountReceipt.setValue(value);

        return bankAccountReceipt;
    }
}
