package com.clevertec.cleverbank.service;

import com.clevertec.cleverbank.dao.*;
import com.clevertec.cleverbank.model.bank.BankAccount;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceipt;
import com.clevertec.cleverbank.model.bank.receipt.BankAccountReceiptType;
import com.clevertec.cleverbank.util.exception.CashWithdrawalException;
import com.clevertec.cleverbank.util.printer.BankAccountReceiptPrinter;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class BankAccountService {

    private final BankAccountDAO accountDAO;
    private final BankAccountReceiptDAO bankAccountReceiptDAO;

    private final BankAccountReceiptPrinter bankAccountReceiptPrinter;

    public void addMoneyToAccount(String accountName, int value) {
        BankAccount bankAccount = accountDAO.findByName(accountName);
        bankAccount.setValue(bankAccount.getValue() + value);
        accountDAO.update(bankAccount.getId(), bankAccount);

        BankAccountReceipt bankAccountReceipt = generateReceipt(bankAccount,value);

        bankAccountReceiptDAO.save(bankAccountReceipt);
        bankAccountReceiptPrinter.print(bankAccountReceipt);
    }

    public void getMoneyFromAccount(String accountName, int value) throws CashWithdrawalException {
        BankAccount bankAccount = accountDAO.findByName(accountName);

        if (bankAccount.getValue() >= value) {
            bankAccount.setValue(bankAccount.getValue() - value);
            accountDAO.update(bankAccount.getId(), bankAccount);

            BankAccountReceipt bankAccountReceipt = generateReceipt(bankAccount,value);

            bankAccountReceiptDAO.save(bankAccountReceipt);
            bankAccountReceiptPrinter.print(bankAccountReceipt);
        } else {
            throw new CashWithdrawalException("Not enough money in the account");
        }
    }

    private BankAccountReceipt generateReceipt(BankAccount bankAccount, int value) {
        BankAccountReceipt bankAccountReceipt = new BankAccountReceipt();

        bankAccountReceipt.setDate(new Date());
        bankAccountReceipt.setType(BankAccountReceiptType.REFILL.toString());
        bankAccountReceipt.setAccount_id(bankAccount.getId());
        bankAccountReceipt.setBank_id(bankAccount.getBank_id());
        bankAccountReceipt.setValue(value);

        return bankAccountReceipt;
    }
}
