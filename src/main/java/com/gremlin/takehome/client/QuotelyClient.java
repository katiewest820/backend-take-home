package com.gremlin.takehome.client;

import com.google.gson.Gson;
import com.gremlin.takehome.pojo.GetQuoteResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuotelyClient {

    private final HttpClient client;
    private final String quotelyUrl;
    private final Gson gson;

    public QuotelyClient(final HttpClient client, final String quotelyUrl) {
        this.client = client;
        this.quotelyUrl = quotelyUrl;
        this.gson = new Gson();
    }

    public String requestQuote(final String language) throws IOException, InterruptedException {
        final String url = String.format(quotelyUrl, language);

        final HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        final HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return gson.fromJson(response.body(), GetQuoteResponse.class).toString();
        }

        return "Unable to fetch quote. API returned status code: " + response.statusCode();
    }
}
