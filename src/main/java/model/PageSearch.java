package model;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import static org.junit.Assert.assertEquals;

// Class for performing search on pages using Lucene
public class PageSearch {
    private StandardAnalyzer analyzer = new StandardAnalyzer(); // Analyzer for text analysis
    private FSDirectory directory = FSDirectory.open(Files.createTempDirectory("tempIndex")); // Directory to store Lucene index

    // Constructor to index pages provided in the map
    public PageSearch(Map<String, Page> pages) throws IOException {
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
        Document doc = new Document();
        doc.add(new StringField("pageTitle", title, Field.Store.YES)); // Store the title
        doc.add(new TextField("pageContent", content, Field.Store.YES)); // Store the content
        writer.addDocument(doc);
    }

    // Method to search pages based on query string
    // Method to search pages based on query string
    public Collection<PageSearchResult> search(String queryStr) throws IOException {

        Collection<PageSearchResult> searchResults = new ArrayList<>();
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory)); // Create IndexSearcher
        try {
            QueryParser contentParser = new QueryParser("pageContent", analyzer);
            QueryParser titleParser = new QueryParser("pageTitle", analyzer);

            // Parse the query string for each field
            Query contentQuery = contentParser.parse(queryStr);
            Query titleQuery = titleParser.parse(queryStr);

            // Create a BooleanQuery to combine the queries for each field
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            builder.add(contentQuery, BooleanClause.Occur.SHOULD); // Include matches from "pageContent"
            builder.add(titleQuery, BooleanClause.Occur.SHOULD); // Include matches from "pageTitle"
            Query combinedQuery = builder.build();

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
}