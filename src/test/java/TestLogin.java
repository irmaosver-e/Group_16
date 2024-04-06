import controller.GuestController;
import external.AuthenticationService;
import external.EmailService;
import model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import view.View;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.System;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


public class TestLogin {

    private Queue<String> inputs = new LinkedList<>();
    private final InputStream systemIn = System.in;
    private SharedContext sharedCont; // Declare sharedCont at the class level
    private View view;
    private AuthenticationService authServ;
    private EmailService emailServ;

    @BeforeEach
    public void setInputs(){
        inputs.add("HermioneGranger"); // Simulated username
        inputs.add("magicwand123");    // Simulated password

        sharedCont = new SharedContext();
        view = new View() {
            @Override
            public String getInput(String input) {
                return inputs.poll(); // Return and remove the head of the queue, simulating user input
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

        authServ = new AuthenticationService() {
            @Override
            public String login(String username, String password) {
                if ("HermioneGranger".equals(username) && "magicwand123".equals(password)) {
                    // Simulate successful authentication response
                    return "{\n" +
                            "  \"username\": \"HermioneGranger\",\n" +
                            "  \"password\": \"magicwand123\",\n" +
                            "  \"email\": \"hermionegranger@hindeburg.ac.uk\",\n" +
                            "  \"role\": \"Student\"\n" +
                            "}\n";
                } else {
                    // Simulate authentication failure
                    return "{\"error\":\"Wrong username or password\"}";
                }
            }
        };

        emailServ = new EmailService() {
            @Override
            public int sendEmail(String sender, String recipient, String subject, String content) {
                return 0;
            }
        };

    }

    @Test
    void testLoginSuccess(){
        GuestController guestController;
        guestController = new GuestController(sharedCont, view, authServ, emailServ);
        guestController.login();
        assertTrue(sharedCont.getCurrentUser() instanceof AuthenticatedUser);
    }

    @Test
    void testIncorrectPassword() {
        // Clear previous inputs and set up for this specific test
        inputs.clear();
        inputs.add("HermioneGranger");
        inputs.add("wrongPassword");
        GuestController guestController;
        guestController = new GuestController(sharedCont, view, authServ, emailServ);

        guestController.login();
        // Check how your application indicates a failed login attempt
        assertTrue(sharedCont.getCurrentUser() instanceof Guest, "User should not be logged in with incorrect password.");
    }

    @Test
    void testUserNotFound() {
        // Setup for non-existing user
        inputs.clear();
        inputs.add("NonExistentUser");
        inputs.add("anyPassword");
        GuestController guestController;
        guestController = new GuestController(sharedCont, view, authServ, emailServ);

        guestController.login();
        // Similar assertion for a failed login due to non-existent user
        assertTrue(sharedCont.getCurrentUser() instanceof Guest, "Non-existent user should not be logged in.");
    }


    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn); // Restore System.in after each test
    }



}