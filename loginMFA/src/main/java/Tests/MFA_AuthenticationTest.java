package Tests;

import com.example.loginmfa.Authentication.MFA_Authentication;
import com.example.loginmfa.Student.UoB_Student;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import junit.framework.TestCase;

import javax.mail.MessagingException;

public class MFA_AuthenticationTest extends TestCase {


    /**
     * Attributes for testing
     */
    UoB_Student testStudent = new UoB_Student("mzmahmo2@bradford.ac.uk",
            "+447592309062",
            "test");

    /**
     * Test that the method returns a 6-digit MFA code
     * @throws Exception errors
     */
    public void testSendSMS_returnsSixDigitCode() throws Exception {
        MFA_Authentication authentication = new MFA_Authentication();
        String code = authentication.sendSMS(testStudent.getPhoneNumber(), new Text());
        // Assertions
        assertEquals(6, code.length());
    }

    /**
     * Test that the method sends an SMS message
     * @throws Exception errors
     */
    public void testSendSMS_sendsMessage() throws Exception {
        MFA_Authentication authentication = new MFA_Authentication();
        Text actionTarget = new Text();
        String code = authentication.sendSMS(testStudent.getPhoneNumber(), actionTarget);
        // Assertions
        assertEquals(testStudent.getPhoneNumber(), "+447592309062");
        assertNotNull(code);
    }

    /**
     * Test that the method handles errors when sending an SMS message (wrongNumber)
     * @throws Exception errors
     */
    public void testSendSMS_handlesErrors() throws Exception {
        MFA_Authentication authentication = new MFA_Authentication();
        Text actionTarget = new Text();
        try {
            String code = authentication.sendSMS("invalid-number", actionTarget);
            fail("Expected exception was not thrown.");
        } catch (Exception e) {
            assertEquals("The 'To' number  is not a valid phone number.", e.getMessage());
        }
    }

    /**
     * Test sending a valid email and code 6 digit
     * @throws Exception errors
     */
    public void testSendEmail_valid() throws Exception {
        MFA_Authentication authentication = new MFA_Authentication();
        Text action_target = new Text();
        String result = authentication.sendEmail(testStudent.getEmail(), action_target);
        assertEquals(testStudent.getEmail(), "mzmahmo2@bradford.ac.uk");
        assertEquals(6, result.length());  // Check that the email code is 6 digits
        assertEquals("Email MFA code sent.", action_target.getText());
        assertEquals(Color.GREEN, action_target.getFill());
    }

    /**
     * Test sending an email with an invalid email address
     * @throws Exception errors
     */
    public void testSendEmail_invalidAddress() throws Exception {
        MFA_Authentication authentication = new MFA_Authentication();
        String email = "invalid-email";
        Text action_target = new Text();
        try {
            authentication.sendEmail(email, action_target);
            fail("Expected MessagingException was not thrown.");
        } catch (MessagingException e) {
            assertEquals("Invalid Addresses", e.getMessage());
        }
    }
}