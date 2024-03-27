package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Guest;
import model.SharedContext;
import view.View;

import java.util.EnumSet;

public class MenuController extends Controller {
    public MenuController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }

    public void mainMenu(){
        this.theView.displayInfo("Welcome");
        if(this.sharedCont.getCurrentUser() instanceof Guest){
            if(GuestMenu());
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
