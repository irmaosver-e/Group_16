package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

import java.util.Collection;
import java.util.EnumSet;

public abstract class Controller {
    protected SharedContext sharedCont;
    protected View theView;
    protected AuthenticationService authServ;
    protected EmailService emailServ;
    protected Controller(SharedContext sharedCont, View theView, AuthenticationService authServ,
                         EmailService emailServ){
        this.sharedCont = sharedCont;
        this.theView = theView;
        this.authServ = authServ;
        this.emailServ = emailServ;
    };

    protected <T extends Enum<T>> int selectFromMenu(Collection<T> menuOptions ,String returninfo){
        int index = 0;
        for(T option : menuOptions) {
            this.theView.displayInfo("input [" + index + "] for: " + menuOptions);  //display index-option pair
            index++;
        }
        this.theView.displayInfo(returninfo);
        try{
            int enter = Integer.parseInt(this.theView.getInput("Enter: "));
            if (enter >= index)
                return Integer.MAX_VALUE;
            return enter;
        }catch (NumberFormatException e){
            return Integer.MAX_VALUE;
        }
    }
}

