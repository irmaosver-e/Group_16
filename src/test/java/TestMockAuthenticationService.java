import external.MockAuthenticationService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TestMockAuthenticationService {
    private MockAuthenticationService authServ;
    @BeforeEach
    public void setUp() throws URISyntaxException, IOException, ParseException {
        authServ = new MockAuthenticationService();
    }
    @Test
    public void testAuthenticationSuccess(){
        String response = authServ.login("HermioneGranger","magicwand123");
        assertNotNull(response,"The login response should not be null.");
        assertTrue(response.contains("HermioneGranger") && response.contains("Student"));
    }

    @Test
    public void testUserNotFound(){
        String response = authServ.login("Hello","magicwand123");
        assertNotNull(response,"The login response should not be null.");
        String expectedResponse = "{\"error\":\"Wrong username or password\"}";
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testIncorrectPassword(){
        String response = authServ.login("HermioneGranger","xxxxxx");
        assertNotNull(response,"The login response should not be null.");
        // Corrected string comparison
        String expectedResponse = "{\"error\":\"Wrong username or password\"}";
        assertEquals(expectedResponse, response);
    }




}
