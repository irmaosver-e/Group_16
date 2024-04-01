import controller.MenuController;
import external.MockAuthenticationService;
import external.MockEmailService;
import model.SharedContext;
import org.json.simple.parser.ParseException;
import view.TextUserInterface;

import java.io.IOException;
import java.net.URISyntaxException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, ParseException {

        TextUserInterface theTextUserInterface = new TextUserInterface();
        SharedContext theSharedContext = new SharedContext();
        MockAuthenticationService theAuthServ = new MockAuthenticationService();
        MockEmailService theEmailServ = new MockEmailService();
        MenuController theMenuController = new MenuController(theSharedContext, theTextUserInterface, theAuthServ, theEmailServ);

        boolean run = true;
        while(run)
        {
            theMenuController.mainMenu();
            System.out.println("\n");
        }
    }

}