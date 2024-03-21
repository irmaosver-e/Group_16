package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

public class TeachingStaffController extends StaffController{

    protected TeachingStaffController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }
}
