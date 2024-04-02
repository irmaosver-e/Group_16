import external.MockAuthenticationService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class TestMockAuthenticationService {
    private MockAuthenticationService authServ;
    @BeforeAll
    public void setUp() throws URISyntaxException, IOException, ParseException {
        authServ = new MockAuthenticationService();
    }
    @Test
    public void testAuthenticationCorrect(){
        assertEquals();
    }


}
