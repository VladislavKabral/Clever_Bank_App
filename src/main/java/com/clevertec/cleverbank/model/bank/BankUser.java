package com.clevertec.cleverbank.model.bank;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankUser {

    private int id;

    private String lastname;

    private String firstname;

    private String middlename;
}
