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
        model.User currentUser= sharedCont.getCurrentUser();

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

    protected Inquiry viewInquiries(Collection<Inquiry> colInquiries) {

        Collection<String> subjects = getInquiryTitles(colInquiries);
        String[] subjects2 = subjects.toArray(new String[0]);

        if (subjects.isEmpty()) {
            System.out.println("There are currently no inquiries available.");
            return null;
        }
        else {

            int count = 0;
            while (count < subjects2.length) {
                String title = subjects2[count];
                System.out.println(count + 1 + ") " + title);
                count = count + 1;
            }
            Inquiry theInquiry=null;
            String choice = theView.getInput("Please input the number of the " +
                    "inquiry you would like to read.");
            String chosenTitle = subjects2[Integer.parseInt(choice) - 1];
            boolean response = false;
            boolean response2 = false;
            while (colInquiries.iterator().hasNext()) {
                Inquiry inquiry = colInquiries.iterator().next();

                if (chosenTitle.equals(inquiry.getSubject())) {
                    theView.displayInquiry(inquiry);
                    theInquiry = inquiry;
                }

            }
            return theInquiry;
        }
    }
}
