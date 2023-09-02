package com.clevertec.cleverbank.model.bank;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankTransaction {

    private int id;

    private String type;

    private int bankSenderId;

    private int bankRecipientId;

    private int accountSenderId;

    private int accountRecipientId;

    private Date date;

    private int value;
}
