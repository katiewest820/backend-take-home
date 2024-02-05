package com.gremlin.takehome;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Configuration {

    private final Properties properties;

    public Configuration() throws IOException {
        this.properties = loadProperties();
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        final InputStream stream = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties");
        properties.load(stream);

        return properties;
    }

    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public String getLanguage(final String input) {
        // Default entry to English if none provided
        // Currently supported languages: English, Russian.
        return Objects.equals(input, "ru") ? input : "en";
    }
}
