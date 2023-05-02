package Tests;

import com.example.loginmfa.Service.LoginService;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import junit.framework.TestCase;

import javax.mail.MessagingException;
import java.io.IOException;

public class Functional_LoginServiceTest extends TestCase {

    /**
     * Incorrect login tests
     * @throws MessagingException, send errors.
     */
    public void testLoginWithIncorrectCredentialsAndSMS() throws MessagingException {
        LoginService login = new LoginService();

        Text action_target = new Text();
        String option = "SMS";
        String email = "example_student@example.com";
        String password = "password";
        String phoneNumber = "1234567890";

        login.Login(new ActionEvent(), action_target, option, email, password, phoneNumber);

        // Assert
        assertEquals("Incorrect username or password.", action_target.getText());
        assertEquals(Color.RED, action_target.getFill());
    }
}