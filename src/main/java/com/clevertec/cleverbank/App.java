package com.clevertec.cleverbank;

import com.clevertec.cleverbank.service.BankAccountService;
import com.clevertec.cleverbank.service.BankTransactionService;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Class which represents entity for working with app
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
public class App {

    /**
     * Field which represents entity for working with bank`s accounts
     */
    private final BankAccountService bankAccountService;

    /**
     * Field which represents entity for working with bank`s transactions
     */
    private final BankTransactionService bankTransactionService;

    /**
     * Field which represents entity for working with user`s input
     */
    private final Scanner scanner;

    /**
     * Method for communicate with user
     */
    public void start() {
        boolean isWork = true;
        int chose = 0;
        boolean isValidInput = false;

        while (isWork) {
            System.out.println("Choose type of operation: ");
            System.out.println("1 - Get cash from account;");
            System.out.println("2 - Add money to account;");
            System.out.println("3 - Send money to another account;");
            System.out.println("4 - Exit.");

            while (!isValidInput) {
                try {
                    chose = scanner.nextInt();
                    isValidInput = true;
                } catch (Exception e) {
                    System.out.println("Incorrect input. Please, input correct number of operation.");
                }
            }

            switch (chose) {
                case 1 -> getCashFromAccount();
                case 2 -> addMoneyToAccount();
                case 3 -> sendMoneyToAnotherAccount();
                case 4 -> isWork = false;
            }
        }
    }

    /**
     * Method for getting money from the account
     */
    private void getCashFromAccount() {
        System.out.println("Enter account name: ");
        String accountName = scanner.nextLine();
        System.out.println("Enter value: ");
        int value = scanner.nextInt();

        try {
            bankAccountService.getMoneyFromAccount(accountName, value);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method for adding money to the account
     */
    private void addMoneyToAccount() {
        System.out.println("Enter account name: ");
        String accountName = scanner.nextLine();
        System.out.println("Enter value: ");
        int value = scanner.nextInt();

        bankAccountService.addMoneyToAccount(accountName, value);
    }

    /**
     * Method for sending money to the account
     */
    private void sendMoneyToAnotherAccount() {
        System.out.println("Enter account-sender name: ");
        String inAccountName = scanner.nextLine();
        System.out.println("Enter account-recipient name: ");
        String outAccountName = scanner.nextLine();
        System.out.println("Enter value of transfer: ");
        int value = scanner.nextInt();

        try {
            bankTransactionService.sendMoneyToAccount(inAccountName, outAccountName, value);
        } catch (NotEnoughMoneyException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
