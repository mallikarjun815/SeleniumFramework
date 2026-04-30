package com.qaproject.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        try {
            FileInputStream file = new FileInputStream(
                    "src/main/resources/config.properties"
            );
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(
                    "config.properties file not found! " + e.getMessage()
            );
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}

