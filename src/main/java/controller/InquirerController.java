package controller;

import external.AuthenticationService;
import external.EmailService;
import model.*;
import view.View;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


public class InquirerController extends Controller{

    public InquirerController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    public void consultFAQ(){
        FAQSection currentSession = null;
    }

    public void SearchPages()
    {
        String searchQuery = theView.getInput("Enter your search query:");
        Map<String, Page> availablePages = sharedCont.getPages();

        //currentUser role is null if user is a Guest
        if(sharedCont.getCurrentUser().getRole() == null)
        {
            for(Map.Entry<String, Page> page : sharedCont.getPages().entrySet())
            {
                //checks if the page is private
                if(page.getValue().isPrivate())
                {
                    availablePages.remove(page.getKey());
                }
            }
        }

        Collection<PageSearchResult> results;
        try
        {
            PageSearch pageSearch = new PageSearch(availablePages);

            results = pageSearch.search(searchQuery);

        }
        catch(IOException e)
        {
            theView.displayException(e);
            return;
        }

        if(results.size() > 4)
        {
            results = (Collection<PageSearchResult>) results.stream().limit(4);
        }

        theView.displaySearchResults(results);
    }
}
