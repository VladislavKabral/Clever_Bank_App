package com.clevertec.cleverbank.model.bank;

import lombok.*;

/**
 * Class which represents bank entity
 *
 * @author Vladislav Kabral
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Bank {

    /**
     * Field which represents bank`s id
     */
    private int id;

    /**
     * Field which represents bank name
     */
    private String name;
}
