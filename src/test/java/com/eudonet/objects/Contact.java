package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Contact {

    private String firstName;
    private String lastName;
    private String function;
    private String civility;
    private long phone;
    private String email;

    public Contact(String firstName, String lastName, String function, String civility, long phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.function = function;
        this.civility = civility;
        this.phone = phone;
        this.email = email;
    }
}
