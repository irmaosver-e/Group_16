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

        //should it have the same subject as the inquirer email?
        String subject=theView.getInput("Input your subject for this email.");

        String content = theView.getInput("Input the content of this email.");

        emailServ.sendEmail(sharedCont.getCurrentUser().getEmail(),
                inquiry.getInquirerEmail(), subject, content);
        //system has to confirm the email was sent

        //the inquiry needs to be deleted from the system
    }
}
