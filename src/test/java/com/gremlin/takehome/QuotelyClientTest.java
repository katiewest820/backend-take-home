package com.gremlin.takehome;

import com.gremlin.takehome.client.QuotelyClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuotelyClientTest {

    @Test
    public void successfulEnRequest() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);

        String httpRes = "{\"quoteText\":\"There is nothing in a caterpillar that tells you it\'s going to be a butterfly.\", \"quoteAuthor\": \"Buckminster Fuller\"}";
        String expectedQuoteRes = "Quote: There is nothing in a caterpillar that tells you it's going to be a butterfly.\n" +
                "Author: Buckminster Fuller";
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(httpRes);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://fake.com?lang=en")).GET().build();

        when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(httpResponse);

        QuotelyClient quotelyClient = new QuotelyClient(httpClient, "http://fake.com?lang=%s");
        String response = quotelyClient.requestQuote("en");
        assertEquals(response, expectedQuoteRes);
    }

    @Test
    public void successfulRuRequest() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);

        String httpRes = "{\"quoteText\": \"Хитрый дерётся, пока мудрый уступает. \",\"quoteAuthor\": \"Карел Чапек\"}";
        String expectedQuoteRes = "Quote: Хитрый дерётся, пока мудрый уступает.\n" +
                "Author: Карел Чапек";
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(httpRes);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://fake.com?lang=ru")).GET().build();

        when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(httpResponse);

        QuotelyClient quotelyClient = new QuotelyClient(httpClient, "http://fake.com?lang=%s");

        String response = quotelyClient.requestQuote("ru");
        assertEquals(response, expectedQuoteRes);
    }

    @Test
    public void failedRequest() throws IOException, InterruptedException {
        HttpClient httpClient = mock(HttpClient.class);

        String expectedRes = "Unable to fetch quote. API returned status code: 400";
        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(400);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create("http://fake.com?lang=ru")).GET().build();

        when(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(httpResponse);
        QuotelyClient quotelyClient = new QuotelyClient(httpClient, "http://fake.com?lang=%s");

        String response = quotelyClient.requestQuote("ru");
        assertEquals(response, expectedRes);
    }
}
