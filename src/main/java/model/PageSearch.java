package model;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.*;

public class PageSearch {

    private Directory index;
    private StandardAnalyzer analyzer;
    private IndexWriterConfig config;

    public PageSearch(Map<String, Page> pages) throws IOException {
        index = new ByteBuffersDirectory();
        analyzer = new StandardAnalyzer();
        config = new IndexWriterConfig(analyzer);

        addDocs(pages);
    }

    private void addDocs(Map<String, Page> pages) throws IOException {
        try (IndexWriter writer = new IndexWriter(index, config)) {
            for (Map.Entry<String, Page> entry : pages.entrySet()) {
                String title = entry.getValue().getTitle();
                String content = entry.getValue().getContent();
                Document doc = new Document();
                doc.add(new org.apache.lucene.document.TextField("title", title, org.apache.lucene.document.Field.Store.YES));
                doc.add(new org.apache.lucene.document.TextField("content", content, org.apache.lucene.document.Field.Store.YES));
                writer.addDocument(doc);
            }
        }
    }

    public Collection<PageSearchResult> search(String queryString) throws IOException, ParseException {
        Map<String, Float> boostFields = new HashMap<>();
        boostFields.put("title", 1.0f); // Boost title field
        boostFields.put("content", 0.5f); // Lower boost for content field

        MultiFieldQueryParser parser = new MultiFieldQueryParser(new String[]{"title", "content"}, analyzer, boostFields);
        Query query;
        try {
            query = parser.parse(queryString);
        } catch (org.apache.lucene.queryparser.classic.ParseException e) {
            throw new IOException("Error parsing query", e);
        }

        try (IndexReader reader = DirectoryReader.open(index)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs topDocs = searcher.search(query, 10); // Limit to 10 results

            Collection<PageSearchResult> results = new ArrayList<>();
            for (int i = 0; i < topDocs.totalHits.value; i++) {
                ScoreDoc scoreDoc = topDocs.scoreDocs[i];
                Document doc = searcher.doc(scoreDoc.doc);
                String title = doc.get("title");
                String content = doc.get("content");
                int matchCounter = i + 1;
                PageSearchResult result = new PageSearchResult("match: " + matchCounter + "\n" +
                        "Title: " + title + "\nContent: " + content);
                results.add(result);
            }
            return results;
        }
    }
}