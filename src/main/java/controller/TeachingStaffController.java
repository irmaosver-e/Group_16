package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Inquiry;
import model.SharedContext;
import view.View;

import java.util.Collection;

public class TeachingStaffController extends StaffController{

    public TeachingStaffController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    protected void viewInquiriesTeachers(Collection<Inquiry> colInquiries) {
            Collection<String> subjects = getInquiryTitles(colInquiries);
            String[] subjects2 = subjects.toArray(new String[0]);

            if (subjects.isEmpty()){
                System.out.println("There are currently no inquiries available.");
            }
            else {
                int count = 0;
                while (count < subjects2.length) {
                    String title = subjects2[count];
                    System.out.println(count + 1 + ") " + title);
                    count = count + 1;
                }
                if (subjects.isEmpty()) {
                    System.out.println("There are currently no inquiries available.");
                }
                String choice = theView.getInput("Please input the " +
                        "number of " +
                        "the " +
                        "inquiry" +
                        " " +
                        "you would like to read.");
                String chosenTitle = subjects2[Integer.parseInt(choice) - 1];
                boolean response = false;

                while (colInquiries.iterator().hasNext()) {
                    Inquiry inquiry = colInquiries.iterator().next();
                    if (chosenTitle.equals(inquiry.getSubject())) {
                        theView.displayInquiry(inquiry);
                        response = theView.getYesNoInput("Would you like to " +
                                "respond to this inquiry?");
                    }
                    if (response) {
                        respondToInquiry(inquiry);
                    }
                }
            }
    }

    public void manageReceivedInquiries(Collection<Inquiry> colInquiries) {
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
                viewInquiriesTeachers(colInquiries);
            }
        }
    }
}
