package com.clevertec.cleverbank.model.bank;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankAccount {

    private int id;

    private String name;

    private int user_id;

    private int bank_id;

    private Date dateOfOpen;

    private int value;
}