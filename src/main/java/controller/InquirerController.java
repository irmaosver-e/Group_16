package controller;

import external.AuthenticationService;
import external.EmailService;
import model.FAQSection;
import model.Page;
import model.PageSearch;
import model.SharedContext;
import view.View;

import java.util.Iterator;
import java.util.Map;


public class InquirerController extends Controller{

    public InquirerController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    public void consultFAQ(){
        FAQSection curretSecion = null;

    }

    public void SearchPages()
    {
        String searchQuery = theView.getInput("Enter your search query:");
        Map<String, Page> availablePages = sharedCont.getPages();

        //currentUser role is null if user is a Guest
        if(sharedCont.getCurrentUser().getRole() == null)
        {
            // Loop over the map using iterator
            Iterator<Map.Entry<String, Page>> pagesIter = availablePages.entrySet().iterator();
            while (pagesIter.hasNext()) {
                Map.Entry<String, Page> entry = pagesIter.next();


                // Check if the current entry meets the condition to be removed
                if (entry.getKey().equals("key2")) {
                    // Remove the current entry using the iterator's remove() method
                    iterator.remove();
                }
            }

            for(Map.Entry<String, Page> page : availablePages.entrySet())
            {
                //checks is the page is private isPrivate
                if(page.getValue().isPrivate())
                {
                    availablePages.re
                    availablePages.remove(page);
                }

                // Create an iterator
                Iterator<Map.Entry<String, Page>> iterator = availablePages.entrySet().iterator();

            }
        }

        PageSearch search = new PageSearch(availablePages);

        //<<exception IOException()>>
        if(IOException)
        {
            getView().displayInfo("exception txt here");
            return;
        }
        else
        {

        }



    }
}
