package Tests;

import com.example.loginmfa.Authentication.MFA_Authentication;
import com.example.loginmfa.Student.UoB_Student;
import com.twilio.exception.TwilioException;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import junit.framework.TestCase;
import org.junit.Before;

import javax.mail.MessagingException;

import static org.junit.Assert.assertNotEquals;

public class MFA_AuthenticationTest extends TestCase {

    /**
     * Attributes for testing
     */
    private MFA_Authentication authentication;
    private UoB_Student testStudent;
    private Text actionTarget;

    @Before
    public void setUp() {
        authentication = new MFA_Authentication();
        testStudent = new UoB_Student("mzmahmo2@bradford.ac.uk",
                "+447592309062",
                "test");
        actionTarget = new Text();
    }

    /**
     * Test that the method returns a 6-digit MFA code
     * @throws Exception errors
     */
    public void testSendSMS_returnsSixDigitCodeAndRegex() throws Exception {
        String code = authentication.sendSMS(testStudent.getPhoneNumber(), new Text());
        assertEquals(6, code.length());
        assertTrue(code.matches("\\d+"));
        assertFalse(code.matches("^0+$"));
    }

    /**
     * Test that the method returns different codes for different phone numbers
     * @throws Exception errors
     */
    public void testSendSMS_returnsDifferentCodesForDifferentPhoneNumbers() throws Exception {
        String code1 = authentication.sendSMS(testStudent.getPhoneNumber(), new Text());
        String code2 = authentication.sendSMS(testStudent.getPhoneNumber(), new Text());
        assertNotEquals(code1, code2);
    }

    /**
     * Test that the method sends an SMS message
     * @throws Exception errors
     */
    public void testSendSMS_sendsMessage() throws Exception {
        String code = authentication.sendSMS(testStudent.getPhoneNumber(), actionTarget);
        assertEquals(testStudent.getPhoneNumber(), "+447592309062");
        assertNotNull(code);
        assertTrue(code.matches("\\d+"));
        assertFalse(code.matches("^0+$"));
    }

    /**
     * Test that the method handles errors when sending an SMS message (wrongNumber)
     * @throws Exception errors
     */
    public void testSendSMS_handlesErrors() throws Exception {
        // Test with invalid number.
        try {
            authentication.sendSMS("invalid-number", actionTarget);
            fail("Expected exception was not thrown.");
        } catch (TwilioException e) {
            assertEquals("The 'To' number  is not a valid phone number.", e.getMessage());
        }

        // Test with null phone number.
        try {
            authentication.sendSMS(null, actionTarget);
            fail("Expected exception was not thrown.");
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"java.lang.CharSequence.toString()\" because \"replacement\" is null", e.getMessage());
        }
    }

    /**
     * Test sending a valid email and code 6 digit
     * @throws Exception errors
     */
    public void testSendEmail_valid() throws Exception {
        String codeEmail = authentication.sendEmail(testStudent.getEmail(), actionTarget);
        assertEquals(testStudent.getEmail(), "mzmahmo2@bradford.ac.uk");
        assertEquals(6, codeEmail.length());
        assertTrue(actionTarget.getText().contains("Email MFA code sent."));
        assertEquals(Color.GREEN, actionTarget.getFill());
        assertTrue(codeEmail.matches("\\d+"));
        assertFalse(codeEmail.matches("^0+$"));
    }

    /**
     * Test sending an email with an invalid email address
     * @throws Exception errors
     */
    public void testSendEmail_invalidAddress() throws Exception {
        // Test with invalid email address format
        try {
            authentication.sendEmail("invalid-email", actionTarget);
            fail("Expected MessagingException was not thrown.");
        } catch (MessagingException e) {
            assertEquals("Invalid Addresses", e.getMessage());
        }

        // Test with empty email address
        try {
            authentication.sendEmail("", actionTarget);
            fail("Expected MessagingException was not thrown.");
        } catch (MessagingException e) {
            assertEquals("No recipient addresses", e.getMessage());
        }

        // Test with null email address
        try {
            authentication.sendEmail(null, actionTarget);
            fail("Expected MessagingException was not thrown.");
        } catch (NullPointerException e) {
            assertEquals("Cannot invoke \"String.length()\" because \"s\" is null", e.getMessage());
        }
    }
}