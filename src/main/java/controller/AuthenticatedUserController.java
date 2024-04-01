package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Guest;
import model.SharedContext;
import view.View;

public class AuthenticatedUserController extends Controller {

    protected AuthenticatedUserController(SharedContext sharedCont, View view, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, view, authServ, emailServ);
    }

    public void logout(){

        // Clear the current user from the shared context
        sharedCont.setCurrentUser(new Guest());

        // Inform the user they have been logged out
        theView.displaySuccess("You have been successfully logged out.");

        // Redirect to the main menu page by calling the mainMenu() method of MenuController - assumption for logout logic
        //menuController.mainMenu();
    }
}
