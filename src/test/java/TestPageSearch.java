import model.Page;
import model.PageSearch;
import model.PageSearchResult;
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
            Collection<PageSearchResult> results = pageSearch.search("Title 1");
            assertEquals(1, results.size());

            PageSearchResult result = results.iterator().next();
            assertTrue(result.getFormattedContent().contains("Title: Title 1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchWithContent() {
        try {
            //Collection<PageSearchResult> results = pageSearch.search( "\"Content of page 2\"\"Content of page 1\"");
            //Collection<PageSearchResult> results = pageSearch.search( "Content \"Content of page 2\" of \"Content of page 1\" page");
            Collection<PageSearchResult> results = pageSearch.search( "Content of page 2");
            assertEquals(1, results.size());

            PageSearchResult result = results.iterator().next();
            assertTrue(result.getFormattedContent().contains("Content: Content of page 2"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchWithNonExistingQuery() {
        try {
            Collection<PageSearchResult> results = pageSearch.search("Non-existing query");
            assertTrue(results.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
