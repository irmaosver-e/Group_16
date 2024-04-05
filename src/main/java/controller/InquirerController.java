package controller;

import external.AuthenticationService;
import external.EmailService;
import model.*;
import view.View;

import java.util.ArrayList;
import java.util.Collection;

public class InquirerController extends Controller{
    protected InquirerController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    public void consultFAQ(){
        FAQSection currentSection = null;
        User currentUser = this.sharedCont.getCurrentUser();
        int option = 0;
        while(!(currentSection==null && option == -1)){
            if (currentSection == null) {
                FAQ faq = this.sharedCont.getFaq();
                currentSection = faq.getFaqSection();
                this.theView.displayFAQ(faq, currentUser instanceof Guest);
                this.theView.displayInfo("[-1] Return to main menu");
            } else {
                this.theView.displayFAQSection(currentSection, currentUser instanceof Guest);
                FAQSection parent = currentSection.getParent();
                this.theView.displayInfo(
                        (parent == null)?"[-1] Return to All Topics":("[-1] Return to " + parent.getTopic())
                );
                manageFAQSubscription(currentSection, currentUser);
            }
            option = getUserOption();
            if (option >= -1) {
                currentSection = FAQNavigation(option, currentSection);
            } else if (option == -2 || option == -3){
                handleSubscriptionUpdates(option, currentSection, currentUser);
            } else{
                this.theView.displayInfo("Invalid Number");
                currentSection = null;
            }
        }

    }
    private void handleSubscriptionUpdates(int optionNo, FAQSection currentSection, User currentUser){
        if(currentUser instanceof AuthenticatedUser){
            String topic = currentSection != null ? currentSection.getTopic() : null;
            if(optionNo == -3) {
                this.theView.displayInfo("Invalid Number");
                return;
            }
            if(topic != null && sharedCont.getFaqTopicsUpdateSubscribers().get(topic) != null && (sharedCont.getFaqTopicsUpdateSubscribers().get(topic).contains(((AuthenticatedUser) currentUser).getEmail()))){
                stopFAQUpdates(((AuthenticatedUser) currentUser).getEmail(), topic);
            }else {
                requestFAQUpdates(((AuthenticatedUser) currentUser).getEmail(), topic);
            }
        }else{
            String topic = currentSection != null ? currentSection.getTopic() : null;
            if(optionNo == -3){
                stopFAQUpdates(null, topic);
            }else if(optionNo == -2){
                requestFAQUpdates(null, topic);
            }
        }
    }

    private void manageFAQSubscription(FAQSection currentSection, User currentUser){
        if(currentUser instanceof AuthenticatedUser){
            String topic = currentSection.getTopic();
            Collection<String> subscribers = this.sharedCont.usersSubscribedTopic(topic);
            if(subscribers != null && subscribers.contains(((AuthenticatedUser) currentUser).getEmail())){
                this.theView.displayInfo("[-2] to stop receiving updates for this topic");
            }else{
                this.theView.displayInfo("[-2] to request updates for this topic");
            }
        }else{ // if is guest
            this.theView.displayInfo("[-2] to request updates for this topic");
            this.theView.displayInfo("[-3] to stop receiving updates for this topic");
        }
    }

    private FAQSection FAQNavigation(int option, FAQSection currentSection) {
        if (option == -1) {
            if(currentSection.getTopic() != null){
                return (currentSection.getParent() == null) ? null : currentSection.getParent();
            }
            return null;
        } else {
            ArrayList<FAQSection> subSections = currentSection.getSubSections();
            if (option >= 0 && option < subSections.size()) {
                return subSections.get(option);
            } else {
                this.theView.displayInfo("Invalid option. Please try again.");
                return currentSection;
            }
        }
    }


    private void requestFAQUpdates(String email, String topic){
        if(email == null){
            email = this.theView.getInput("Please enter your email address");
        }
        boolean success = this.sharedCont.registerForFAQUpdates(email, topic);
        if(success){
            this.theView.displaySuccess("Successfully registered " + email + " for updates on " + topic);
        }else{
            this.theView.displayFailure("Failed to register " + email + " for updates on " + topic + " Perhaps this email was already registered?");
        }
    }

    private void stopFAQUpdates(String email, String topic){
        if(email == null){
            email = this.theView.getInput("Please enter your email address");
        }
        boolean success = this.sharedCont.unregisterForFAQUpdates(email, topic);
        if(success){
            this.theView.displaySuccess("Successfully unregistered " + email + " for updates on " + topic + "");
        }else{
            this.theView.displayFailure("Failed to unregister " + email + " for updates on " + topic + " Perhaps this email was not registered?");
        }
    }

    private int getUserOption(){
        try{
            return Integer.parseInt(this.theView.getInput("Please choose an option"));
        }catch(NumberFormatException e){
            this.theView.displayException(e);
            return Integer.MAX_VALUE;
        }
    }




    public void contactStaff() {
        String inquirerEmail;
        // Checking if user is logged in
        if (sharedCont.getCurrentUser() != null){
            inquirerEmail = ((AuthenticatedUser)this.sharedCont.getCurrentUser()).getEmail();
        }
        else {
            inquirerEmail = theView.getInput("Please enter your email address:");
        }
        // Prompt for the subject and content of the inquiry
        String subject = theView.getInput("Please enter the subject of your inquiry:");
        String content = theView.getInput("Please enter the content of your inquiry:");
        Inquiry newInquiry = new Inquiry(inquirerEmail,subject,content);
        sharedCont.unAnsweredInquiries.add(newInquiry);
        theView.displaySuccess("Inquiry submitted successfully!");

        String adminEmail = sharedCont.ADMIN_STAFF_EMAIL;
        String notifSubject = "New inquiry:"+subject;
        String notifContent = " Please log in to the Self Service Portal to review.";
        emailServ.sendEmail(inquirerEmail,adminEmail,notifSubject,notifContent);

    }

    public void SEARCH_PAGES(){

    }

    public void CONTACT_STAFF(){

    }
}
