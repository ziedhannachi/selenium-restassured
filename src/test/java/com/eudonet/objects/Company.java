package com.eudonet.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Company {

    private String companyName;
    private String street1;
    private long phone;
    private String city;
    private String country;
    private String zipCode;

    public Company(String companyName, String street1, long phone, String city, String country, String zipCode) {
        this.companyName = companyName;
        this.street1 = street1;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }
}
