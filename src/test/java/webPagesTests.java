import model.Page;
import model.PageSearch;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class webPagesTests {

    private final InputStream originalIn = System.in;
    private final String lineSeparator = System.lineSeparator();
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PageSearch pageSearch;
    private Map<String, Page> pages;

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
    public void AddPageTest() throws URISyntaxException, IOException, ParseException {
        // Redirect System.in to provide dynamic input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                ("0\n" +
                        "DrunkPanda\nbamboozled2022\n" +
                        "2\nThe Amazing Spiderman\nSpider-Man, Spider-Man. Does whatever a spider can.\nno\n\n" +
                        "2\nGodzilla\nGo! Go! Go! Godzilla Godzilla! Godzilla!\nno\n\n" +
                        "2\nJames Bond\nShe wants to be your James Bond.\nyes\n" +
                        "q\n").getBytes());
        System.setIn(inputStream);

        // Call the main method
        Main.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();
        // Check if the output contains the specific string
        boolean emailSentNotification = output.contains("Email from drunkpanda@hindeburg.ac.uk to jack.tr@hindenburg.ac.uk");
        boolean page1Added = output.contains("Added Page The Amazing Spiderman");
        boolean page2Added = output.contains("Added Page Godzilla");
        boolean page3Added = output.contains("Added Page James Bond");
        Assertions.assertTrue(emailSentNotification && page1Added && page2Added && page3Added,
                "Failed to add Pages");
    }

    @Test
    public void OverridePageTest() throws URISyntaxException, IOException, ParseException {
        // Redirect System.in to provide dynamic input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                ("0\n" +
                        "DrunkPanda\nbamboozled2022\n" +
                        "2\nThe Amazing Spiderman\nSpider-Man, Spider-Man. Does whatever a spider can.\nno\n\n" +
                        "3\n\n" +
                        "2\nThe Amazing Spiderman\nSpins a web, any size. Catches thieves just like flies.\nyes\n" +
                        "yes\n\n" +
                        "3\nq\n").getBytes());
        System.setIn(inputStream);

        // Call the main method
        Main.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        // Remove carriage return characters from the output string
        output = output.replaceAll("\r", "");

        // Check if the output contains the specific string
        boolean emailSentNotification = output.contains("Email from drunkpanda@hindeburg.ac.uk to jack.tr@hindenburg.ac.uk");
        boolean page1Added = output.contains("Added Page The Amazing Spiderman");
        boolean showPages = output.contains("Title: The Amazing Spiderman\nContent: Spider-Man, Spider-Man. Does whatever a spider can.");
        boolean overrideMessage = output.contains("Page The Amazing Spiderman already exists. Overwrite with new page?");
        boolean showOvewritenPrivPage = output.contains("This page is private.\n" +
                                                    "Title: The Amazing Spiderman\n" +
                                                    "Content: Spins a web, any size. Catches thieves just like flies.");
        boolean expectedResult = emailSentNotification && page1Added && showPages && overrideMessage && showOvewritenPrivPage;
        Assertions.assertTrue(expectedResult,"Failed to add override Pages");
    }

    @Test
    public void guestSearchPrivatePageTest() throws URISyntaxException, IOException, ParseException {
        // Redirect System.in to provide dynamic input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                ("0\n" +
                        "DrunkPanda\nbamboozled2022\n" +
                        "2\nThe Amazing Spiderman\nSpider-Man, Spider-Man. Does whatever a spider can.\nno\n\n" +
                        "2\nThe Amazing Spiderman 2\nSpins a web, any size.Catches thieves just like flies.\nno\n\n" +
                        "2\nThe Amazing Spiderman 3\nLook out. Here comes the Spider-Man.\nno\n\n" +
                        "2\nThe Amazing Spiderman 4\nIn the chill of night. At the scene of a crime\nyes\n\n" +
                        "2\nThe Amazing Spiderman 5\nLike a streak of light. He arrives just in time.\nyes\n\n" +
                        "0\n\n2\nSpiderman\nq\n").getBytes());
        System.setIn(inputStream);
        // Call the main method
        Main.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        // Remove carriage return characters from the output string
        output = output.replaceAll("\r", "");

        // Check if the output contains the specific string
        boolean page1 = output.contains("match: 1\nTitle: The Amazing Spiderman\n" +
                "Content: Spider-Man, Spider-Man. Does whatever a spider can.");
        boolean page2 = output.contains("match: 2\nTitle: The Amazing Spiderman 2\n" +
                "Content: Spins a web, any size.Catches thieves just like flies.");
        boolean page3 = output.contains("match: 3\nTitle: The Amazing Spiderman 3\n" +
                "Content: Look out. Here comes the Spider-Man.");
        boolean priv1 = output.contains("match: 4\nTitle: The Amazing Spiderman 4\n" +
                "Content: In the chill of night. At the scene of a crime");
        boolean priv2 = output.contains("match: 5\nTitle: The Amazing Spiderman 5\n" +
                "Content: Like a streak of light. He arrives just in time.");

        boolean expectedResult = page1 && page2 && page3 && !priv1 && !priv2;
        Assertions.assertTrue(expectedResult,"Private pages showing");
    }

    @Test
    public void studentSearchPrivatePageAnd4ResultsLimitTest() throws URISyntaxException, IOException, ParseException {
        // Redirect System.in to provide dynamic input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                ("0\n" +
                        "DrunkPanda\nbamboozled2022\n" +
                        "2\nThe Amazing Spiderman\nSpider-Man, Spider-Man. Does whatever a spider can.\nno\n\n" +
                        "2\nThe Amazing Spiderman 2\nSpins a web, any size.Catches thieves just like flies.\nno\n\n" +
                        "2\nThe Amazing Spiderman 3\nLook out. Here comes the Spider-Man.\nno\n\n" +
                        "2\nThe Amazing Spiderman 4\nIn the chill of night. At the scene of a crime\nyes\n\n" +
                        "2\nThe Amazing Spiderman 5\nLike a streak of light. He arrives just in time.\nyes\n\n" +
                        "0\n\n0\nMrBeanFan\nbeanstalk2023\n2\nSpiderman\nq\n").getBytes());
        System.setIn(inputStream);
        // Call the main method
        Main.main(new String[]{});

        String output = outputStreamCaptor.toString().trim();

        // Remove carriage return characters from the output string
        output = output.replaceAll("\r", "");

        // Check if the output contains the specific string
        boolean page1 = output.contains("Title: The Amazing Spiderman\n" +
                "Content: Spider-Man, Spider-Man. Does whatever a spider can.");
        boolean page2 = output.contains("Title: The Amazing Spiderman 2\n" +
                "Content: Spins a web, any size.Catches thieves just like flies.");
        boolean page3 = output.contains("Title: The Amazing Spiderman 3\n" +
                "Content: Look out. Here comes the Spider-Man.");
        boolean priv1 = output.contains("Title: The Amazing Spiderman 4\n" +
                "Content: In the chill of night. At the scene of a crime");
        boolean priv2 = output.contains("Title: The Amazing Spiderman 5\n" +
                "Content: Like a streak of light. He arrives just in time.");

        boolean expectedResult = page1 && page2 && page3 && (priv1 || priv2);
        Assertions.assertTrue(expectedResult,"Private pages showing");
    }
}
