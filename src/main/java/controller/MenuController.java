package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Guest;
import model.SharedContext;
import view.View;

import java.util.EnumSet;

public class MenuController extends Controller {
    private AuthenticatedUserController authUserCtrler;
    private GuestController guestCtrler ;
    private StaffController staffCtrler;
    private InquirerController inquirerCtrler;

    public MenuController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    public void mainMenu(){
        theView.displayInfo("Welcome");

        guestCtrler = new GuestController(sharedCont, theView, authServ, emailServ);

        if(theView.getInput("Continue a Guest? Yes/No").equals("Yes")){
            GuestMenu();
        }
        else
        {
            guestCtrler.login();
        }
    }


    private boolean GuestMenu(){
        GuestController guest = new GuestController(this.sharedCont,this.theView,this.authServ,this.emailServ);

        this.theView.displayInfo("Guest Menu:");
        try{
            int index = 0;
            EnumSet<GuestMainMenuOption> menuOptions = EnumSet.allOf(GuestMainMenuOption.class);
            for(GuestMainMenuOption option : menuOptions){
                this.theView.displayInfo("input ["+index+"] for: "+menuOptions);
            }


        }catch (NumberFormatException e){
            this.theView.displayException(e);
        }
        return true;
    }
}
