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
            boolean response = false;
            if (chooseToView) {
                Inquiry inquiry = viewInquiries(colInquiries);
                response = theView.getYesNoInput("Would you like to " +
                        "respond to this inquiry?");
                if (response) {
                    respondToInquiry(inquiry);
                }
            }
        }
    }
}
