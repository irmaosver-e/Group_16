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

public class TestAnswerInquiry {
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
    public void testMainSuccessAdminAnswers() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nyes" +
                "\ncontent" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message should be output that the email was sent.",
                "\u001B[0mYour email" +
                        " has been sent " +
                        "successfully.",
                theOutput[length - 3]);
    }

    @Test
    public void testEmptyAdminAnswer() throws URISyntaxException, IOException,
            ParseException {
        setUpOutput();
        //the input for the email content will be empty at first then will be
        // "content" to show that system will ask again.
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nyes" +
                "\n\ncontent" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message should be output that the email was sent.",
                "\u001B[0mYour email has been sent successfully.",
                theOutput[length - 3]);
    }

    @Test
    public void testTeachingStaffChoosesNotToAnswer() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nBatmanFanatic\ndarkknight2020\n1\nyes\n1\nno\nyes" +
                "\npikachufan23@hindeburg.ac.uk\n \n0\n " +
                "\n0\nPikachuFan23\nthunderbolt456\n1\nyes\n1\nno\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertNotEquals("\u001B[0mYour email has been sent successfully.",
                theOutput[length - 3], "No message should be output about an " +
                        "email " +
                        "being sent.");
    }
    @Test
    public void testMainSuccessScenarioTeachingAnswers() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nBatmanFanatic\ndarkknight2020\n1\nyes\n1\nno\nyes" +
                "\npikachufan23@hindeburg.ac.uk\n \n0\n " +
                "\n0\nPikachuFan23\nthunderbolt456\n1\nyes\n1\nyes\ncontent" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message is output about the email being sent.",
                "\u001B[0mYour email " +
                        "has been sent successfully.",
                theOutput[length - 3]);
    }

    @Test
    public void testEmptyTeachingAnswer() throws URISyntaxException,
            IOException,
            ParseException {
        setUpOutput();
        //the input for the email content will be empty at first then will be
        // "content" to show that system will ask again.
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nBatmanFanatic\ndarkknight2020\n1\nyes\n1\nno\nyes" +
                "\npikachufan23@hindeburg.ac.uk\n \n0\n " +
                "\n0\nPikachuFan23\nthunderbolt456\n1\nyes\n1\nyes\n\ncontent" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message should be output that the email was sent.",
                "\u001B[0mYour email has been sent successfully.",
                theOutput[length - 3]);
    }

    @Test
    public void testInquiryIsDeletedAdmin() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nyes" +
                "\ncontent\n\n1\nyes" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message should be output that no inquiries are " +
                        "available.","There are currently no " +
                        "inquiries available.",
                theOutput[length - 3]);
    }

    @Test
    public void testInquiryIsDeletedTeaching() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nBatmanFanatic\ndarkknight2020\n1\nyes\n1\nno\nyes" +
                "\npikachufan23@hindeburg.ac.uk\n \n0\n " +
                "\n0\nPikachuFan23\nthunderbolt456\n1\nyes\n1\nyes\ncontent\n\n1\nyes" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message should be output that no inquiries are " +
                        "available.","There are currently no " +
                        "inquiries available.",
                theOutput[length - 3]);
    }
}
