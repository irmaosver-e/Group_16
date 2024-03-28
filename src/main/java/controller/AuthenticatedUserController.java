package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

public class AuthenticatedUserController extends Controller {
    private View view;
    private SharedContext sharedContext;
    private MenuController menuController;
    protected AuthenticatedUserController(SharedContext sharedCont, View view, AuthenticationService authServ, EmailService emailServ) {
        super(sharedCont, view, authServ, emailServ);
    }

    public void logout(){
        // Clear the current user from the shared context
        sharedContext.setCurrentUser(null);

        // Inform the user they have been logged out
        view.displaySuccess("You have been successfully logged out.");

        // Redirect to the main menu page by calling the mainMenu() method of MenuController - assumption for logout logic
        menuController.mainMenu();
    }
}
