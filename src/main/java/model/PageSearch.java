package model;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import static org.junit.Assert.assertEquals;

// Class for performing search on pages using Lucene
public class PageSearch {
    private KeywordAnalyzer analyzer = new KeywordAnalyzer (); // Analyzer for text analysis
    private FSDirectory directory = FSDirectory.open(Files.createTempDirectory("tempIndex")); // Directory to store Lucene index

    private ArrayList<String> queryStrings = new ArrayList<>();

    private boolean hasExactMatchQuery = false;
    private boolean hasLooseQuery = false;
    // Constructor to index pages provided in the map
    public PageSearch(Map<String, Page> pages) throws IOException {

        // Clear the Lucene index directory
        for (String file : directory.listAll()) {
            Files.deleteIfExists(directory.getDirectory().resolve(file));
        }

        // Index pages
        IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        try {
            for (Map.Entry<String, Page> entry : pages.entrySet()) {
                Page page = entry.getValue();
                addDoc(writer, page.getTitle(), page.getContent());
            }
        } finally {
            writer.close(); // Close the IndexWriter after adding all documents
        }
    }

    // Method to add a document to the index
    private void addDoc(IndexWriter writer, String title, String content) throws IOException {
        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS); // Include positional data
        fieldType.setStored(true);
        fieldType.setTokenized(true);
        fieldType.setStoreTermVectors(true); // Store term vectors for phrase queries

        Document doc = new Document();
        doc.add(new StringField("pageTitle", title, Field.Store.YES)); // Store the title
        doc.add(new Field("pageContent", content, fieldType)); // Store the content with the configured field type
        writer.addDocument(doc);
    }

    // Method to search pages based on query string
    public Collection<PageSearchResult> search(String queryStr) throws IOException {

        Collection<PageSearchResult> searchResults = new ArrayList<>();
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory)); // Create IndexSearcher

        try {
            // Extract exact match and non-exact match queries
            stringExtractor(queryStr);

            // Create a BooleanQuery to combine the queries for each field
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            QueryParser contentParser = new QueryParser("pageContent", analyzer);
            QueryParser titleParser = new QueryParser("pageTitle", analyzer);

            int lastQueryStrIndex = hasLooseQuery ? queryStrings.size() - 1 : queryStrings.size();
            //int lastQueryStrIndex = queryStrings.size() - 1;
            if(hasExactMatchQuery)
            {
                // Iterate over each query string and create exact match phrase queries
                for (int i = 0; i < lastQueryStrIndex; i++) {
                    Query contentQuery = contentParser.parse("\"" + queryStrings.get(i) + "\"");
                    Query titleQuery = titleParser.parse("\"" + queryStrings.get(i) + "\"");
                    // Combine the queries
                    builder.add(contentQuery, BooleanClause.Occur.SHOULD); // Require exact match for content
                    builder.add(titleQuery, BooleanClause.Occur.SHOULD); // Require exact match for title
                }
            }

            if(hasLooseQuery)
            {
                Query contentQuery = contentParser.parse(queryStrings.get(lastQueryStrIndex));
                Query titleQuery = titleParser.parse(queryStrings.get(lastQueryStrIndex));

                // Combine the non-exact match query
                builder.add(contentQuery, BooleanClause.Occur.SHOULD); // Include matches from "pageContent"
                builder.add(titleQuery, BooleanClause.Occur.SHOULD); // Include matches from "pageTitle"
            }

            BooleanQuery combinedQuery = builder.build();
            ScoreDoc[] hits = searcher.search(combinedQuery, 10).scoreDocs; // Perform search, limit to 10 results

            // Process search results
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = searcher.doc(hits[i].doc); // Get entire document corresponding to hit
                // Retrieve title and content separately
                String title = hitDoc.get("pageTitle");
                String content = hitDoc.get("pageContent");
                // Create PageSearchResult with title and content
                int matchCounter = i + 1;
                PageSearchResult pageSearchResult = new PageSearchResult("match: " + matchCounter + "\n" +
                        "Title: " + title + "\nContent: " + content);
                searchResults.add(pageSearchResult); // Add search result to collection
            }
        } catch (ParseException e) {
            throw new RuntimeException(e); // Throw runtime exception if parsing fails
        } finally {
            searcher.getIndexReader().close(); // Close IndexReader

        }
        return searchResults; // Return search results
    }
    private void stringExtractor(String query)
    {
        // Define the pattern to match the substring between quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        // Create a matcher to find the matches in the original string
        Matcher matcher = pattern.matcher(query);

        // Find the starting index of each match and extract the substring before it
        String nonExactQuery = "";
        int lastIndex = 0;
        while (matcher.find()) {
            // save substrings enclosed in double quotes
            queryStrings.add(matcher.group(1));
            int startIndex = matcher.start();
            nonExactQuery = nonExactQuery + query.substring(lastIndex, startIndex).trim() + " ";
            lastIndex = matcher.end();

            //sets the hasExactMatchQuery flag
            if(!hasExactMatchQuery) hasExactMatchQuery = true;
        }
        // Extract the remaining substring after the last match
        if(!query.substring(lastIndex).isEmpty())
        {
            if(!hasLooseQuery) hasLooseQuery = true;
            nonExactQuery = nonExactQuery + query.substring(lastIndex).trim();
            queryStrings.add(nonExactQuery);
        }
    }

}