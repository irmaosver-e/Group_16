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

    public void addPAge()
    {

        String title = theView.getInput("Enter page title");
        String content = theView.getInput("Enter page content");
        boolean isPrivate = theView.getYesNoInput("Should this page be private?");

        Map<String, Page> availablePages = sharedCont.getPages();

        boolean titleExists = availablePages.containsKey("title");


        if(titleExists)
        {
            boolean overwrite = theView.getYesNoInput("Page" + title + "already exists. Overwrite with new page?");

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
                    "redirection", "Your email has been redirected to another" +
                    " member of staff.");

            emailServ.sendEmail(inquiry.getInquirerEmail(), recipient,
                inquiry.getSubject(), inquiry.getEmailContent());

            System.out.println("The email has successfully been redirected.");
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
