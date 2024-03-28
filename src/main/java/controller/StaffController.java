package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Inquiry;
import model.SharedContext;
import view.View;

import java.util.ArrayList;
import java.util.Collection;

public class StaffController extends Controller{
    public StaffController(SharedContext sharedCont, View theView, AuthenticationService authServ,
                           EmailService emailServ){
        super(sharedCont, theView,authServ,emailServ);
    }

    protected Collection<String> getInquiryTitles(Collection<Inquiry> colInquiries)
    {
        Collection<String> colStr = new ArrayList<>();
        for (Inquiry inquiry : colInquiries) {
            colStr.add(inquiry.getSubject());
        }
        return colStr;
    }



    protected void respondToInquiry(Inquiry inquiry){
        //check user is logged in
        boolean login;
        model.User currentUser= sharedCont.getCurrentUser();
        if (currentUser.getRole() ==null){
            login=false;
        }
        else{
            login=true;
        }
        if (login) {
            String subject = inquiry.getSubject();

            String content = null;

            while (content == null) {
                content = theView.getInput("Input the content of this email.");
            }
            int success = emailServ.sendEmail(currentUser.getEmail(),
                    inquiry.getInquirerEmail(), subject, content);
            //system has to confirm the email was sent
            if (success == 0) {
                System.out.println("Your email has been sent successfully.");
                sharedCont.getUnAnsweredInquiries().remove(inquiry);
            } else if (success == 1) {
                System.out.println("Your email is invalid.");
            } else if (success == 2) {
                System.out.println("The receiver email is invalid.");
            } else {
                System.out.println("The email status is unknown.");
            }
        }
    }
}
