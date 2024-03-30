package controller;

import external.AuthenticationService;
import external.EmailService;
import model.*;
import view.View;

import java.util.Iterator;
import java.util.Map;


public class InquirerController extends Controller{

    private SharedContext sharedContext;
    private View view;

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

        public void contactStaff() {
            String inquirerEmail;
            // Checking if user is logged in
            if (sharedContext.getCurrentUser() != null){
               inquirerEmail = sharedContext.getCurrentUser().getEmail();
            }
            else {
                inquirerEmail = view.getInput("Please enter your email address:");
            }
           // Prompt for the subject and content of the inquiry
           String subject = view.getInput("Please enter the subject of your inquiry:");
           String content = view.getInput("Please enter the content of your inquiry:");
           Inquiry newInquiry = new Inquiry(inquirerEmail,subject,content);
           sharedContext.unAnsweredInquiries.add(newInquiry);
           view.displaySuccess("Inquiry submitted successfully!");

           String adminEmail = sharedContext.ADMIN_STAFF_EMAIL;
           String notifSubject = "New inquiry:"+subject;
           String notifContent = " Please log in to the Self Service Portal to review.";
           emailServ.sendEmail(inquirerEmail,adminEmail,notifSubject,notifContent);

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
