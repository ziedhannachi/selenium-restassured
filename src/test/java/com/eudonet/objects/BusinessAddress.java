package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BusinessAddress {

    private String company;
    private String street1;
    private long phone;
    private String city;
    private String country;
    private String email;

    public BusinessAddress(String company, String street1, long phone, String city, String country, String email) {
        this.company = company;
        this.street1 = street1;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.email = email;
    }
}
