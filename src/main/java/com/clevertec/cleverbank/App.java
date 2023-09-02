package com.clevertec.cleverbank;

import com.clevertec.cleverbank.service.BankAccountService;
import com.clevertec.cleverbank.util.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class App {

    private final BankAccountService bankAccountService;
    private final Scanner scanner;

    public void start() {
        boolean isWork = true;
        int chose = 0;
        boolean isValidInput = false;

        while (isWork) {
            System.out.println("Choose type of operation: ");
            System.out.println("1 - Get cash from account;");
            System.out.println("2 - Add money to account;");
            System.out.println("3 - Exit.");

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
                case 3 -> isWork = false;
            }
        }
    }

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

    private void addMoneyToAccount() {
        System.out.println("Enter account name: ");
        String accountName = scanner.nextLine();
        System.out.println("Enter value: ");
        int value = scanner.nextInt();

        bankAccountService.addMoneyToAccount(accountName, value);
    }
}
