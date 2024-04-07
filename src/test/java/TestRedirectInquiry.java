import external.MockAuthenticationService;
import external.MockEmailService;
import model.SharedContext;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import view.TextUserInterface;
import view.View;

import java.io.*;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestRedirectInquiry {
    private static SharedContext sharedCont;
    private static TextUserInterface textUserInt;
    private static MockAuthenticationService authServ;
    private static MockEmailService emailServ;
    private static View theView;

    @BeforeAll
    public static void setUp() throws URISyntaxException, IOException, ParseException {
        emailServ = new MockEmailService();
        sharedCont = new SharedContext();
        authServ = new MockAuthenticationService();
        textUserInt = new TextUserInterface();
    }
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @Before
    public void setUpOutput(){
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    private void provideInput(String data){
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput(){
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput(){
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    @Test
    public void testMainSuccessAdminRedirects() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nno" +
                "\nyes\npikachufan23@hindeburg.ac.uk" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("The email should be successfully redirected.","\u001B" +
                        "[0mThe " +
                        "email has successfully " +
                        "been " +
                        "redirected.",
                theOutput[length-3]);
    }

    @Test
    public void testNullEmailRedirect() throws URISyntaxException, IOException,
            ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nno" +
                "\nyes\n\ndarthvaderfan@hindeburg.ac.uk" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("The email should be successfully redirected.","\u001B" +
                        "[0mThe " +
                        "email has successfully " +
                        "been " +
                        "redirected.",
                theOutput[length-3]);
    }

    @Test
    public void testNotAnEmailRedirect() throws URISyntaxException, IOException,
            ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nno" +
                "\nyes\ninvalidEmail\ndarthvaderfan@hindeburg.ac.uk" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("The email should be successfully redirected.","\u001B" +
                        "[0mThe " +
                        "email has successfully " +
                        "been " +
                        "redirected.",
                theOutput[length-3]);
    }

    @Test
    public void testChooseNotToRedirect() throws URISyntaxException,
            IOException,
            ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nno" +
                "\nno\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertNotEquals("The email should be successfully redirected.",
                "\u001B" +
                        "[0mThe " +
                        "email has successfully " +
                        "been " +
                        "redirected.",
                theOutput[length-3]);
    }


}
