package com.clevertec.cleverbank.model.bank;

import lombok.*;

/**
 * Class which represents bank user entity
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BankUser {

    /**
     * Field which represents user`s id
     */
    private int id;

    /**
     * Field which represents user`s lastname
     */
    private String lastname;

    /**
     * Field which represents user`s firstname
     */
    private String firstname;

    /**
     * Field which represents user`s middle name
     */
    private String middlename;
}
