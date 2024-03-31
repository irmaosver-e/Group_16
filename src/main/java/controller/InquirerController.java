package controller;

import external.AuthenticationService;
import external.EmailService;
import model.*;
import view.View;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

// Controller class responsible for handling actions related to inquirers
public class InquirerController extends Controller {

    // Constructor for InquirerController
    public InquirerController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    // Method to consult Frequently Asked Questions (FAQs)
    public void consultFAQ() {
        // Placeholder for the current session's FAQ section
        FAQSection currentSession = null;
    }

    // Method to search pages based on user input
    public void searchPages() {
        // Prompt user to enter search query
        String searchQuery = theView.getInput("Enter your search query:");
        // Retrieve available pages from shared context
        Map<String, Page> availablePages = sharedCont.getPages();

        // If the current user is a guest (i.e., their role is null), filter out private pages
        if (sharedCont.getCurrentUser().getRole() == null) {
            for (Map.Entry<String, Page> page : sharedCont.getPages().entrySet()) {
                // Check if the page is private, remove it from availablePages if so
                if (page.getValue().isPrivate()) {
                    availablePages.remove(page.getKey());
                }
            }
        }

        Collection<PageSearchResult> results;
        try {
            // Perform page search using availablePages
            PageSearch pageSearch = new PageSearch(availablePages);
            results = pageSearch.search(searchQuery);
        } catch (IOException e) {
            // Display exception if an IO error occurs during the search
            theView.displayException(e);
            return;
        }

        // Limit the number of search results to 4
        if (results.size() > 4) {
            results = (Collection<PageSearchResult>) results.stream().limit(4);
        }

        // Display search results to the user
        theView.displaySearchResults(results);
    }
}
