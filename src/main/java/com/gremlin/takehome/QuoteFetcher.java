package com.gremlin.takehome;

import com.gremlin.takehome.client.QuotelyClient;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Duration;

public class QuoteFetcher {

    private final QuotelyClient quotelyClient;

    public QuoteFetcher(String url, String timeout) {
        this.quotelyClient = new QuotelyClient(initializeClient(timeout), url);
    }

    private HttpClient initializeClient(String timeout) {
        return HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(Long.parseLong(timeout)))
                .build();
    }

    public String getQuote(final String language) throws IOException, InterruptedException {
        return quotelyClient.requestQuote(language);
    }
}
