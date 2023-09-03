package com.clevertec.cleverbank.model.bank;

import lombok.*;

import java.util.Date;

/**
 * Class which represents bank account entity
 *
 * @author Vladislav Kabral
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankAccount {

    /**
     * Field which represents account`s id
     */
    private int id;

    /**
     * Field which represents account name
     */
    private String name;

    /**
     * Field which represents account owner`s id
     */
    private int user_id;

    /**
     * Field which represents bank account`s id
     */
    private int bank_id;

    /**
     * Field which represents account`s date of open
     */
    private Date dateOfOpen;

    /**
     * Field which represents current state of account
     */
    private int value;
}