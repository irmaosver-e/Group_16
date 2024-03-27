package controller;

import external.AuthenticationService;
import external.EmailService;
import model.AuthenticatedUser;
import model.SharedContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.View;

public class GuestController extends Controller {
    public GuestController(SharedContext sharedCont, View theView, AuthenticationService authServ,
                           EmailService emailServ){
        super(sharedCont, theView,authServ,emailServ);
    }

    public void login() {
        String username = theView.getInput("Enter your name");
        String password = theView.getInput("Enter your password");
        String response = authServ.login(username, password);
        if (response.contains("error")){
            theView.displayError(response);
            return;
        }
        try{
            JSONParser parser = new JSONParser();
            JSONObject user = (JSONObject) parser.parse(response);
            String email = (String) user.get("email");

            String role = (String) user.get("role");

            AuthenticatedUser currentUser = new AuthenticatedUser(email, role);

            if (email == null){
                throw new IllegalArgumentException("User email cannot be full");
            }
            if (role == null | (role != "Student" & role != "AdminStaff" & role != "TeachingStaff")){
                throw new IllegalArgumentException("Unsupported user role");
            }

            sharedCont.setCurrentUser(currentUser);
            theView.displaySuccess("login successful");

        } catch (IllegalArgumentException | ParseException e) {
            theView.displayException(e);

        }
    }


}
