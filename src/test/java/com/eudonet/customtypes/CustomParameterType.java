package com.eudonet.customtypes;

import com.eudonet.constants.Language;
import io.cucumber.java.ParameterType;

public class CustomParameterType {

    @ParameterType(".*")
    public Language language(String languageName) {
        return Language.valueOf(languageName);
    }
}
