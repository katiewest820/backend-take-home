package com.gremlin.takehome;

import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		try {
			final Configuration configuration = new Configuration();
			final String language = configuration.getLanguage(args.length == 0 ? null : args[0]);

			final String timeout = configuration.getProperty("http.client.timeout.seconds");
			final String url = configuration.getProperty("quotely_url");

			final QuoteFetcher quoteFetcher = new QuoteFetcher(url, timeout);
			final String quote = quoteFetcher.getQuote(language);

			// Logging out requested quote
			System.out.println("----------------------------------------------------");
			System.out.println(quote);
			System.out.println("----------------------------------------------------");
		} catch (final IOException|InterruptedException e) {
			System.out.println("Unexpected error occurred while making request " + e.getMessage());
		}
	}
}
