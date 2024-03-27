package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

public abstract class Controller {
    protected SharedContext sharedCont;
    protected View theView;
    protected AuthenticationService authServ;
    protected EmailService emailServ;
    protected Controller(SharedContext sharedCont, View theView, AuthenticationService authServ,
                         EmailService emailServ){};

    //protected <T>
}
