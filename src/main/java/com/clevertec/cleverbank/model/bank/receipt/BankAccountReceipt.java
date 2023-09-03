package com.clevertec.cleverbank.model.bank.receipt;

import lombok.*;

import java.util.Date;

/**
 * Class which represents receipt after operations 'get' and 'add' money for bank account
 *
 * @author Vladislav Kabral
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankAccountReceipt {

    /**
     * Field which represents receipt`s id
     */
    private int id;

    /**
     * Field which represents receipt`s type
     */
    private String type;

    /**
     * Field which represents date of operation
     */
    private Date date;

    /**
     * Field which represents bank of operation
     */
    private int bank_id;

    /**
     * Field which represents account of operation
     */
    private int account_id;

    /**
     * Field which represents value of money in the operation
     */
    private int value;

    /**
     * Field which represents counter of receipts
     */
    public static int RECEIPT_COUNTER;
}
