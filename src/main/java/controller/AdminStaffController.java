package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Page;
import model.SharedContext;
import view.View;
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

}
