
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;


import java.io.*;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class TestViewReceivedInquiries {


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
    public void testMainSuccessScenarioAdmin() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nHermioneGranger\nmagicwand123\n3" +
                "\nsubject\ncontent\n " +
                "\n0\n0\nMickeyMouseFan\nhappiestplace789\n1\nyes\n1\nno\nno" +
                "\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("The case where there is one inquiry titles subject","1)" +
                " subject", theOutput[length-9]);
        assertEquals("Sender: hermionegranger@hindeburg.ac.uk", theOutput[length-7]);
        assertEquals("Subject: subject", theOutput[length-6]);
        assertEquals("Content: content", theOutput[length-5]);
        assertEquals("Would you like to respond to this inquiry?",
                theOutput[length - 4]);
        assertEquals("Would you like to redirect this inquiry?",
                theOutput[length - 3]);
    }



    @Test
    public void testMainSuccessScenarioTeaching() throws URISyntaxException,
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
        assertEquals("The case where there is one inquiry titled subject.",
                "1) subject",
                theOutput[length-8]);
        assertEquals("Sender: hermionegranger@hindeburg.ac.uk",
                theOutput[length-6]);
        assertEquals("Subject: subject", theOutput[length-5]);
        assertEquals("Content: content", theOutput[length-4]);
        assertEquals("Would you like to respond to this inquiry?", theOutput[length - 3]);
    }

    @Test
    public void testNoInquiriesAvailableAdmin() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nMickeyMouseFan\nhappiestplace789\n1\nyes\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message is returned that there are no inquiries " +
                        "available.", "There are currently no inquiries available.",
                theOutput[length - 3]);
    }

    @Test
    public void testNoInquiriesAvailableTeaching() throws URISyntaxException,
            IOException, ParseException {
        setUpOutput();
        final String testInput = "0\nPikachuFan23\nthunderbolt456\n1\nyes\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;
        assertEquals("A message is returned that there are no " +
                        "inquiries.", "There are currently no inquiries available.",
                theOutput[length - 3]);
    }
}
