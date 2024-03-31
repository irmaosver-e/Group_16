package model;

// Class representing a search result from a page search
public class PageSearchResult {
    private String formattedContent; // Formatted content of the search result

    // Constructor to initialize a PageSearchResult object with rawResult as formatted content
    public PageSearchResult(String rawResult) {
        this.formattedContent = rawResult;
    }

    // Getter method to retrieve the formatted content of the search result
    public String getFormattedContent() {
        return formattedContent;
    }
}
