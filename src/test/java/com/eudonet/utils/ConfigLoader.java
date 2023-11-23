package com.eudonet.utils;

import com.eudonet.constants.EnvType;

import java.util.Properties;

import static com.eudonet.constants.Constants.DEV_CONFIG_PROPERTIES_PATH;
import static com.eudonet.constants.Constants.TEST_CONFIG_PROPERTIES_PATH;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.DEV));
        switch (EnvType.valueOf(env)) {
            case DEV:
                properties = PropertyUtils.propertyLoader(DEV_CONFIG_PROPERTIES_PATH);
                break;
            case TEST:
                properties = PropertyUtils.propertyLoader(TEST_CONFIG_PROPERTIES_PATH);
                break;
            default:
                throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("Property baseUl is not specified in properties file.");
        }
    }
}
