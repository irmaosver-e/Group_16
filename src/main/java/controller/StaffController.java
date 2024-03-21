package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Inquiry;
import model.SharedContext;
import view.View;

import java.util.ArrayList;
import java.util.Collection;

public class StaffController extends Controller {
    public StaffController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ)
    {
        super(sharedCont, theView, authServ, emailServ);
    }

    protected Collection<String> getInquiryTitles(Collection<Inquiry> colInquiries)
    {
        Collection<String> colStr = new ArrayList<>();
        for (Inquiry inquiry : colInquiries) {
            colStr.add(inquiry.getSubject());
        }
        return colStr;
    }

}
