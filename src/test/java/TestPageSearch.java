import model.Page;
import model.PageSearch;
import model.PageSearchResult;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPageSearch {

    private PageSearch pageSearch;
    private Map<String, Page> pages;

    @BeforeEach
    void setUp() {
        pages = new HashMap<>();
        pages.put("page1", new Page("Title 1", "Content of page 1",false));
        pages.put("page2", new Page("Title 2", "Content of page 2",false));
        pages.put("page3", new Page("Title 3", "Content of page 3",false));

        try {
            pageSearch = new PageSearch(pages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchWithTitle() {
        try {
            Collection<PageSearchResult> results = pageSearch.search("\"Title 1\"");
            assertEquals(1, results.size(), "Expected exactly one search result for query 'Title 1'");

            PageSearchResult result = results.iterator().next();
            assertTrue(result.getFormattedContent().contains("Title: Title 1"),
                    "Expected search result to contain title 'Title 1'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSimpleQuerySearch() {
        try {
            Collection<PageSearchResult> results = pageSearch.search("Title");
            assertEquals(3, results.size(), "Expected all three pages");

            PageSearchResult result = results.iterator().next();
            assertTrue(result.getFormattedContent().contains("Title: Title"),
                    "Expected search result to contain title 'Title'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testSearchWithContent() {
        try {
            Collection<PageSearchResult> results = pageSearch.search("\"Content of page 2\"");
            assertEquals(1, results.size(), "Expected exactly one search result for query 'Content of page 2'");

            PageSearchResult result = results.iterator().next();
            assertTrue(result.getFormattedContent().contains("Content: Content of page 2"),
                    "Expected search result to contain content 'Content of page 2'");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchWithNonExistingQuery() {
        try {
            Collection<PageSearchResult> results = pageSearch.search("Non-existing query");
            assertTrue(results.isEmpty(), "Expected no search results for non-existing query");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
