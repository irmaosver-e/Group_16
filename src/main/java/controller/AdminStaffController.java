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
            theView.displaySuccess("Added Page " + title + "");
        }
        else
        {
            theView.displayWarning("Added Page " + title + " but failed to send email notification!");
        }

    }

    private void redirectInquiry(Inquiry inquiry){

            String recipient = null;
            boolean problem=true;
            while (recipient==null || problem) {
                recipient = theView.getInput("Please input the email address " +
                        "of the " +
                        "person you would like to redirect this email to.");

                //send notification to recipient email of redirection
                String sender = sharedCont.ADMIN_STAFF_EMAIL;
                int checkSent = emailServ.sendEmail(sender, recipient,
                        "Notification of email " +
                                "redirection", "An inquiry has been redirected to you. " +
                                "The subject line is: " + inquiry.getSubject() + " " +
                                "\nPlease" +
                                " log " +
                                "in to the Self Service Portal to review this inquiry.");
                if (checkSent == 0) {
                    System.out.println("The email has successfully been redirected.");
                    problem = false;
                } else if (checkSent == 2) {
                    System.out.println("The recipient email is incorrect.");
                } else {
                    System.out.println("The sender email is incorrect.");
                }
            }
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

            if (page.isPrivate()) {
                theView.displayInfo("This page is private.");
            }

            // Assuming Page class has getTitle and getContent methods
            theView.displayInfo("Title: " + page.getTitle());
            theView.displayInfo("Content: " + page.getContent());

            //adds a space between entries
            theView.displayInfo("");
        }
    }

    public void manageFAQ(){

    }


        public void manageInquiries() {
            boolean login;
            Collection<Inquiry> colInquiries =
                    sharedCont.getUnAnsweredInquiries();
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
                    if (inquiry!=null) {
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
}
