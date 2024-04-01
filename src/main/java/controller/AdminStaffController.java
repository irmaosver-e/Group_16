package controller;

import external.AuthenticationService;
import external.EmailService;
import external.MockEmailService;
import model.Inquiry;
import model.Page;
import model.SharedContext;
import view.View;

import java.util.Collection;
import java.util.Map;

public class AdminStaffController extends StaffController{

    protected AdminStaffController(SharedContext sharedCont, View theView, AuthenticationService authServ,
                                   EmailService emailServ){
        super(sharedCont, theView,authServ,emailServ);
    }

    public void addPage()
    {

        String title = theView.getInput("Enter page title");
        String content = theView.getInput("Enter page content");
        boolean isPrivate = theView.getYesNoInput("Should this page be private?");

        Map<String, Page> availablePages = sharedCont.getPages();

        boolean titleExists = availablePages.containsKey(title);

        if(titleExists)
        {
            boolean overwrite = theView.getYesNoInput("Page " + title + " already exists. Overwrite with new page?");

            if(!overwrite)
            {
                theView.displayInfo("Cancelled adding new page");
                return;
            }

        }

        Page newPage = new Page(title, content, isPrivate);
        sharedCont.addPage(newPage);

        int status = emailServ.sendEmail(sharedCont.getCurrentUser().getEmail(), sharedCont.ADMIN_STAFF_EMAIL,
                "...", "...");

        if(status == EmailService.STATUS_SUCCESS)
        {
            theView.displaySuccess("Added Page" + title + "");
        }
        else
        {
            theView.displayWarning("Added Page" + title + "but failed to send email notification!");
        }

    }

    private void redirectInquiry(Inquiry inquiry){

            String recipient = null;

            while (recipient==null) {
                recipient = theView.getInput("Who would you like to redirect " +
                        "this email to?");
            }
            //send notification to recipient email of redirection
            String sender = "automatic email system";
            emailServ.sendEmail(sender, recipient, "Notification of email " +
                    "redirection", "An inquiry has been redirected to you. " +
                    "The subject line is: " + inquiry.getSubject() + " Please" +
                    " log " +
                    "in to the Self Service Portal to review this inquiry.");

            System.out.println("The email has successfully been redirected.");
    }

    public void viewAllPages(){
        Map<String, Page> availablePages = sharedCont.getPages();

        // Check if there are any pages to display
        if (availablePages.isEmpty()) {
            theView.displayInfo("There are no pages available.");
            return;
        }

        // Display each page
        for (Map.Entry<String, Page> pageEntry : availablePages.entrySet()) {
            Page page = pageEntry.getValue();
            // Assuming Page class has getTitle and getContent methods
            theView.displayInfo("Title: " + page.getTitle());
            // Check if the page is not private or if the current user has permission to view it
            if (!page.isPrivate()) {  // Implement userHasPermission as needed
                theView.displayInfo("Content: " + page.getContent());
            } else {
                theView.displayInfo("This page is private.");
            }

            //adds a space between entries
            theView.displayInfo("");
        }
    }

    public void manageFAQ(){

    }


        public void manageInquiries(Collection<Inquiry> colInquiries) {
            boolean login;
            model.User currentUser = sharedCont.getCurrentUser();
            if (currentUser.getRole() == null) {
                login = false;
            } else {
                login = true;
            }

            if (login) {

                boolean chooseToView = theView.getYesNoInput("Would you like to view " +
                        "the unanswered inquiries?");
                if (chooseToView) {
                    Inquiry inquiry = viewInquiries(colInquiries);
                    boolean response = false;
                    boolean response2 = false;
                    response = theView.getYesNoInput("Would you like to " +
                            "respond to this inquiry?");
                    if (response) {
                        respondToInquiry(inquiry);
                    } else {
                        response2 = theView.getYesNoInput("Would you like to " +
                                "redirect this inquiry?");
                        if (response2) {
                            redirectInquiry(inquiry);
                        }
                    }

                }
            }
        }
}
