import external.MockEmailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class MockEmailServiceTest {
    private static MockEmailService emailServ;
    @BeforeAll
    public static void setUp(){
        emailServ = new MockEmailService();
    }
    @Test
    public void testEmailServiceSuccess(){
        assertEquals(0, emailServ.sendEmail("barb78916@hindenburg.ac.uk",
                "jack.tr@hindenburg.ac.uk", "subject", "content"));
    }

    @Test
    public void testWrongSenderEmail(){
        assertEquals(1, emailServ.sendEmail("wrong email", "jack.tr@hindenburg.ac.uk", "subject", "content"));
    }

    @Test
    public void testWrongRecipientEmail(){
        assertEquals(2, emailServ.sendEmail("barb78916@hindenburg.ac.uk",
                "wrong email", "subject", "content"));
    }

    @Test
    public void testTeacherReplyEmail(){
        assertEquals(0, emailServ.sendEmail("pranksterpete@hindeburg.ac.uk", "thejoker@hindeburg.ac.uk", "subject", "content"));
    }

    @Test
    public void testAdminReplyEmail(){
        assertEquals(0, emailServ.sendEmail("jack.tr@hindenburg.ac.uk",
                "hermionegranger@hindeburg.ac.uk", "subject", "content"));
    }

    @Test
    public void testAdminNotif(){
        assertEquals(0, emailServ.sendEmail("jack.tr@hindenburg.ac.uk",
                "pikachufan23@hindeburg.ac.uk", "subject", "content"));
    }
}
