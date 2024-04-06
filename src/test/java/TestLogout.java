import controller.AuthenticatedUserController;
import controller.GuestController;
import external.AuthenticationService;
import external.EmailService;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.View;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class TestLogout {

    private Queue<String> inputs = new LinkedList<>();
    private SharedContext sharedCont; // Declare sharedCont at the class level
    private View view;
    private AuthenticationService authServ;
    private EmailService emailServ;

    private AuthenticatedUserController controller;

    @BeforeEach
    public void setInputs(){
        sharedCont = new SharedContext();
        authServ = null;
        emailServ = null;
        view = new View() {
            @Override
            public String getInput(String input) {
                return null;
            }

            @Override
            public boolean getYesNoInput(String input) {
                return false;
            }

            @Override
            public void displayInfo(String info) {

            }

            @Override
            public void displaySuccess(String success) {
                System.out.println(success);
            }

            @Override
            public void displayFailure(String fail) {

            }

            @Override
            public void displayWarning(String warning) {

            }

            @Override
            public void displayError(String error) {

            }

            @Override
            public void displayException(Exception e) {

            }

            @Override
            public void displayDivider() {

            }

            @Override
            public void displayFAQ(FAQ faq, Boolean includeSections) {

            }

            @Override
            public void displayFAQSection(FAQSection faqSection, Boolean includeQuestions) {

            }

            @Override
            public void displayInquiry(Inquiry inquiry) {

            }

            @Override
            public void displaySearchResults(Collection<PageSearchResult> results) {

            }
        };
    }

    @Test
    public void testLogoutSuccess(){
        AuthenticatedUserController controller;
        controller = new AuthenticatedUserController(sharedCont, view, authServ, emailServ);
        controller.logout();
        assertTrue(sharedCont.getCurrentUser() instanceof Guest);
    }

    @Test
    public void testWhenAlreadyLoggedOut(){
        sharedCont.setCurrentUser(new Guest());
        AuthenticatedUserController controller;
        controller = new AuthenticatedUserController(sharedCont, view, authServ, emailServ);
        controller.logout();
        assertTrue(sharedCont.getCurrentUser() instanceof Guest);
    }


}
