package com.eudonet.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public long generateRandomNumber() {
        Faker faker = new Faker();
        return faker.number().randomNumber(5, true);
    }

    public String generateFirstName() {
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public String generateLastName() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }
}
