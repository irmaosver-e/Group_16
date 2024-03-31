package model;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
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
        for (Map.Entry<String, Page> entry : pages.entrySet()) {
            String pageID = entry.getKey();
            Page page = entry.getValue();
            addDoc(writer, pageID, page.getContent());
        }
        writer.close();
    }

    // Method to add a document to the index
    private void addDoc(IndexWriter writer, String pageID, String content) throws IOException {
        try (writer) {
            Document doc = new Document();
            doc.add(new Field("pageId", pageID, TextField.TYPE_STORED)); // Add page ID as stored field
            doc.add(new Field("content", content, TextField.TYPE_STORED)); // Add content as stored field
            writer.addDocument(doc);
        }
    }

    // Method to search pages based on query string
    public Collection<PageSearchResult> search(String queryStr) throws IOException {
        Collection<PageSearchResult> searchResults = new ArrayList<>();
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory)); // Create IndexSearcher
        try {
            QueryParser parser = new QueryParser("content", analyzer); // Create query parser
            Query query = parser.parse(queryStr); // Parse query string
            ScoreDoc[] hits = searcher.search(query, 10).scoreDocs; // Perform search, limit to 10 results

            // Process search results
            StoredFields storedFields = searcher.storedFields();
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = storedFields.document(hits[i].doc); // Get document corresponding to hit
                // Create PageSearchResult with page ID and content
                PageSearchResult pageSearchResult = new PageSearchResult(hitDoc.getField("pageId").toString() + "\n" +
                        hitDoc.getField("content").toString());
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