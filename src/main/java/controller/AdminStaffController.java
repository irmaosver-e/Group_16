package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

public class AdminStaffController extends StaffController{

    protected AdminStaffController(SharedContext sharedCont, View theView, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, theView, authServ, emailServ);
    }
}
