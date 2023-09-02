package com.clevertec.cleverbank.model.bank.receipt;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankAccountReceipt {

    private int id;

    private String type;

    private Date date;

    private int bank_id;

    private int account_id;

    private int value;

    public static int RECEIPT_COUNTER = 0;
}
