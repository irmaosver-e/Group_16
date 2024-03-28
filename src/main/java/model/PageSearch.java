package model;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class PageSearch {
    private Map<String, Page> pages;
    public PageSearch(Map<String, Page> pages) throws IOException
    {
        if (pages == null)
        {
            throw new IOException("pages object null");
        }
        this.pages = pages;
    }

    public Collection<PageSearchResult> search(String query) throws IOException
    {
        if(pages.isEmpty())
        {
            throw new IOException("pages object empty");
        }

        //do search based on query using lucene

        return null;
    }
}
