package com.eudonet.constants;

public enum Language {
    FRENCH("Français"),
    ENGLISH("English"),
    GERMAN("Deutsch"),
    DUTCH("Nederlands"),
    SPANISH("Español"),
    ITALIAN("Italiano");

    public final String name;

    Language(String name) {
        this.name = name;
    }
}
