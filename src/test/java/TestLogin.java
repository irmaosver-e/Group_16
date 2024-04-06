import controller.GuestController;
import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import view.View;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.System;




public class TestLogin {

    private final InputStream systemIn = System.in ;
    private ByteArrayInputStream testIn;

    private SharedContext sharedCont;
    private View view;
    private AuthenticationService authServ;

    private EmailService emailServ;

    @BeforeEach
    public void SetUpOutput(){
        String simulatedUserInput =  "HermioneGranger\nmagicwand123\n"; // Backup System.in to restore it later
        testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
        sharedCont = new SharedContext();
    }

    @AfterEach
    public void restoreSystemInputOutput(){
        System.setIn(systemIn);
    }

    @Test
    void testLoginSuccess(){
        GuestController guestController;
        guestController = new GuestController(sharedCont, view, authServ, emailServ);
        guestController.login();
        assertNotNull(sharedCont.getCurrentUser());
    }

}
