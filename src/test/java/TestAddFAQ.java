import external.MockEmailService;
import model.SharedContext;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.*;
import java.net.URISyntaxException;

public class TestAddFAQ {
    private static SharedContext sharedCont;
    @BeforeAll
    public static void setUp(){
        sharedCont = new SharedContext();
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
    public void testAddFAQMainSuccessScenario() throws URISyntaxException,
            IOException,
            ParseException {
        setUpOutput();
        final String testInput = "0\nMickeyMouseFan\nhappiestplace789\n4" +
                "\n-2\na question\nthe answer"+
                "\nthe subtopic\n-1\nq";
        provideInput(testInput);
        Main.main(new String[0]);
        String[] theOutput = getOutput().split("\r\n");
        int length = theOutput.length;

    }
}
