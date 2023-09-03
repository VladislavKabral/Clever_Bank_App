package com.clevertec.cleverbank.model.bank;

import lombok.*;

import java.util.Date;

/**
 * Class which represents transaction entity
 *
 * @author Vladislav Kabral
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankTransaction {

    /**
     * Field which represents transaction`s id
     */
    private int id;

    /**
     * Field which represents type of transaction
     */
    private String type;

    /**
     * Field which represents id of bank-sender
     */
    private int bankSenderId;

    /**
     * Field which represents id of bank-recipient
     */
    private int bankRecipientId;

    /**
     * Filed which represents id of account-sender
     */
    private int accountSenderId;

    /**
     * Field which represents id of account-recipient
     */
    private int accountRecipientId;

    /**
     * Field which represents date of transaction
     */
    private Date date;

    /**
     * Field which represents value of transaction
     */
    private int value;
}
