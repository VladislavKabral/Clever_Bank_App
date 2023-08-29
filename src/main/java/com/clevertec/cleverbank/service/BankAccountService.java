package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.dao.*;
import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceiptType;

import java.util.Date;

public class BankAccountService {

    private final BankAccountDAO accountDAO;
    private final BankAccountReceiptDAO bankAccountReceiptDAO;

    public BankAccountService(BankAccountDAO accountDAO, BankAccountReceiptDAO bankAccountReceiptDAO) {
        this.accountDAO = accountDAO;
        this.bankAccountReceiptDAO = bankAccountReceiptDAO;
    }

    public void addMoneyToAccount(String accountName, int value) {
        BankAccount bankAccount = accountDAO.findByName(accountName);
        BankAccountReceipt bankAccountReceipt = new BankAccountReceipt();
        bankAccountReceipt.setDate(new Date());
        bankAccountReceipt.setType(BankAccountReceiptType.REFILL.toString());
        bankAccountReceipt.setAccount_id(bankAccount.getId());
        bankAccountReceipt.setBank_id(bankAccount.getBank_id());
        bankAccountReceipt.setValue(value);

        bankAccountReceiptDAO.save(bankAccountReceipt);
    }
}
