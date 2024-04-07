package controller;

import external.AuthenticationService;
import external.EmailService;
import external.MockEmailService;
import model.*;
import view.View;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;


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
                        "teaching staff you would like to redirect this email" +
                        " to.");
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

    public void manageFAQ() {
        FAQSection currentFAQSection = this.sharedCont.getFaq().getFaqSection();

        while (true) {
            this.theView.displayFAQSection(currentFAQSection, true);
            this.theView.displayInfo("-1 to Return");
            this.theView.displayInfo("-2 to add question answer pair");
            int op = Integer.parseInt(this.theView.getInput("Navigate?"));
            if (op == -1) { // return to privous layer / cancel
                if (currentFAQSection.getParent() != null) {
                    currentFAQSection = currentFAQSection.getParent();
                    this.theView.displayInfo("Parent topic");
                } else {
                    this.theView.displayInfo("You are on the root section, return to menu");
                    return;
                }
            }
            if (op == -2) { // Add Q-A
                addFAQItem(currentFAQSection);
            }
            try {
                currentFAQSection = currentFAQSection.getSubSections().get(op);
                this.theView.displayInfo("Sub topic");
            } catch (IndexOutOfBoundsException e) {
                this.theView.displayInfo("invalid index, please enter again");
            }
        }
    }

    public void addFAQItem(FAQSection faqSection){
        String question = this.theView.getInput("Question:");
        String answer = this.theView.getInput("Answer:");
        String emailtopic = faqSection.getTopic();

        // ask for topic
        if(faqSection.getTopic() != null){ // not root
            boolean isNewTopic = this.theView.getYesNoInput("Add a new subTopic?");
            if(isNewTopic){
                String topic = this.theView.getInput("new Subtopic name:");
                emailtopic = topic;
                FAQSection toAddSec;
                if(!faqSection.getAllSubTopics().contains(topic)){
                    toAddSec = new FAQSection(topic);
                    toAddSec.addItem(question,answer);
                    faqSection.addSubsection(toAddSec);
                }else{
                    this.theView.displayWarning("Topic already exist, add to old one.");
                    toAddSec = faqSection.getSubSectionWithTopic(topic);
                    toAddSec.addItem(question,answer);
                }
            }else{
                faqSection.addItem(question,answer);
            }
        }else { // root
            String topic = this.theView.getInput("new Subtopic name:");
            emailtopic = topic;
            FAQSection toAddSec;
            if(!faqSection.getAllSubTopics().contains(topic)){
                toAddSec = new FAQSection(topic);
                toAddSec.addItem(question,answer);
                faqSection.addSubsection(toAddSec);
            }else{
                this.theView.displayWarning("Topic already exist, add to old one.");
                toAddSec = faqSection.getSubSectionWithTopic(topic);
                toAddSec.addItem(question,answer);
            }
        }
        // sends email
        String subject = "FAQ Topic " + emailtopic + " Updated";
        String content = faqSection.printItems(emailtopic);
        this.emailServ.sendEmail(((AuthenticatedUser)this.sharedCont.getCurrentUser()).getEmail(), SharedContext.ADMIN_STAFF_EMAIL, subject, content);
        if(this.sharedCont.usersSubscribedTopic(emailtopic) != null){
            for(String subscriber : this.sharedCont.usersSubscribedTopic(emailtopic)){
                this.emailServ.sendEmail(SharedContext.ADMIN_STAFF_EMAIL, subscriber, subject, content);
            }
        }
    }


        public void manageInquiries() {
            boolean login;
            Collection<Inquiry> colInquiries =
                    sharedCont.getUnAnsweredInquiries();
            model.User currentUser = sharedCont.getCurrentUser();
            if (((AuthenticatedUser)currentUser).getRole() == null) {
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
