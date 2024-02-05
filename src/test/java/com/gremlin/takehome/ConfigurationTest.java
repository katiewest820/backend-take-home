package com.gremlin.takehome;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfigurationTest {

    @Test
    public void getLanguageTest() throws IOException {
        Configuration configuration = new Configuration();

        String english = configuration.getLanguage("en");
        String russian = configuration.getLanguage("ru");
        String nullString = configuration.getLanguage(null);
        String invalidEntry = configuration.getLanguage(null);

        assertEquals(english, "en");
        assertEquals(russian, "ru");
        assertEquals(nullString, "en");
        assertEquals(invalidEntry, "en");
    }
}
