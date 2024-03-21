package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

import java.util.Collection;

public class Controller {
    protected Controller(SharedContext sharedCont, View theView, AuthenticationService authServ,
                         EmailService emailServ){};

    protected <T> int selectFromMenu(Collection<T> collection, String str){
        return 0;
    };
}
