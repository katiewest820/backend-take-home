package com.gremlin.takehome.pojo;

public class GetQuoteResponse {

    private String quoteText;
    private String quoteAuthor;

    public GetQuoteResponse(String quoteText, String quoteAuthor) {
        this.quoteText = quoteText;
        this.quoteAuthor = quoteAuthor;
    }

    @Override
    public String toString() {
        return "Quote: " + quoteText.trim() + "\n" +  "Author: " + quoteAuthor.trim();
    }
}
