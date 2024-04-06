import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.TextUserInterface;

import java.io.*;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemTests {

    private final InputStream originalIn = System.in;
    private final String lineSeparator = System.lineSeparator();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void adminStaffAddPAge() throws URISyntaxException, IOException, ParseException {

        // Redirect System.in to provide dynamic input
            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                    ("0\n" +
                            "DrunkPanda\n" +
                            "bamboozled2022\n" +
                            "2\n" +
                            "The Amazing Spiderman\n" +
                            "Spider-Man, Spider-Man. Does whatever a spider can.\n" +
                            "no\n" +
                            "\n" +
                            "2\n" +
                            "Godzilla\n" +
                            "Go! Go! Go! Godzilla Godzilla! Godzilla!\n" +
                            "no\n" +
                            "\n" +
                            "2\n" +
                            "James Bond\n" +
                            "She wants to be your James Bond.\n" +
                            "yes\n" +
                            "q\n").getBytes());
            System.setIn(inputStream);

        // Call the main method
        Main.main(new String[]{});

        String test = outputStreamCaptor.toString().trim();
        String output = outputStreamCaptor.toString().trim();
        // Check if the output contains the specific string
        boolean emailSentNotification = output.contains("Email from drunkpanda@hindeburg.ac.uk to jack.tr@hindenburg.ac.uk");
        boolean page1Added = output.contains("Added Page The Amazing Spiderman");
        boolean page2Added = output.contains("Added Page Godzilla");
        boolean page3Added = output.contains("Added Page James Bond");
        assertTrue(emailSentNotification && page1Added && page2Added && page3Added);
    }
}
