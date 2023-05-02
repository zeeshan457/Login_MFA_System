package com.example.loginmfa.Service;

import com.example.loginmfa.Authentication.MFA_Authentication;
import com.example.loginmfa.Dialog.createDialog;
import com.example.loginmfa.Student.UoB_Student;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;


public class LoginService {

    /**
     * Attributes
     */
    private MFA_Authentication MFA = new MFA_Authentication();
    private createDialog dialog = new createDialog();

    // Declaring student object
    private UoB_Student example_student = new UoB_Student
            ("mzmahmo2@bradford.ac.uk"
            , "+447592309062"
            , "test");

    /**
     * Constructor, injecting MFA
     */
    public LoginService() {
    }

    /**
     *
     * Handles the login for the MFA system, checks if the email and password
     * matches the user, and then checks for MFA options selected and sends
     * the code after the login, either SMS or Email.
     *
     * @param event        the ActionEvent that triggered the login
     * @param action_target display the login result
     * @param option       containing the MFA options
     * @param email        user's email address
     * @param password     user's password
     * @param phoneNumber  user's phoneNumber
     */
    public void Login(ActionEvent event, Text action_target, String option, String email,
                      String password, String phoneNumber) throws MessagingException {

        // Check if credentials are correct
        if (email.equals(example_student.getEmail()) && password.equals(example_student.getPassword()) &&
                phoneNumber.equals(example_student.getPhoneNumber())) {

    //============================================================================================//
    //================================== Windows / Android section ===============================//
    //============================================================================================//

            TextInputDialog androidDialog = dialog.createWindowsDialog();
            Optional<String> resultPlatform = androidDialog.showAndWait();
            String platform = resultPlatform.orElse("");
            if (platform.equals("1")) {
                // Send the MFA code via the selected option
                if (option.equals("SMS")) {

                    // Calling SMS Authentication
                    String SMScode = MFA.sendSMS(example_student.getPhoneNumber(), action_target);

                    // Prompt the user to enter the MFA code
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("SMS Authentication");
                    dialog.setHeaderText("Enter the 6-digit code sent to your " + option + ": " + phoneNumber);
                    dialog.setContentText("Code:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && result.get().equals(SMScode)) {
                        action_target.setFill(Color.GREEN);
                        action_target.setText("MFA Authorised.");
                    } else {
                        action_target.setFill(Color.RED);
                        action_target.setText("Incorrect MFA code.");
                    }

                } else if (option.equals("Email")) {
                    // Calling Email Authentication
                    String emailCode = MFA.sendEmail(example_student.getEmail(), action_target);

                    // Prompt the user to enter the MFA code
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Email Authentication");
                    dialog.setHeaderText("Enter the 6-digit code sent to your " + option + ": " + email);
                    dialog.setContentText("Code:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && result.get().equals(emailCode)) {
                        action_target.setFill(Color.GREEN);
                        action_target.setText("MFA Authorised.");
                    } else {
                        action_target.setFill(Color.RED);
                        action_target.setText("Incorrect MFA code.");
                    }
                }
            } else if (platform.equals("2")) {

                // Send the MFA code via the selected option
                if (option.equals("SMS")) {

                    // Calling SMS Authentication
                    String SMScode = MFA.sendSMS(example_student.getPhoneNumber(), action_target);

                    // Prompt the user to enter the MFA code
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("SMS Authentication");
                    dialog.setHeaderText("Enter the 6-digit code sent to your " + option + ": " + phoneNumber);
                    dialog.setContentText("Code:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && result.get().equals(SMScode)) {
                        action_target.setFill(Color.GREEN);
                        action_target.setText("MFA Authorised.");
                    } else {
                        action_target.setFill(Color.RED);
                        action_target.setText("Incorrect MFA code.");
                    }

                } else if (option.equals("Email")) {
                    // Calling Email Authentication
                    String emailCode = MFA.sendEmail(example_student.getEmail(), action_target);

                    // Prompt the user to enter the MFA code
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Email Authentication");
                    dialog.setHeaderText("Enter the 6-digit code sent to your " + option + ": " + email);
                    dialog.setContentText("Code:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent() && result.get().equals(emailCode)) {
                        action_target.setFill(Color.GREEN);
                        action_target.setText("MFA Authorised.");
                    } else {
                        action_target.setFill(Color.RED);
                        action_target.setText("Incorrect MFA code.");
                    }
                }
            } else {
                action_target.setFill(Color.RED);
                action_target.setText("Please type 1 or 2 for a selected platform");
            }



        } else {
            action_target.setFill(Color.RED);
            action_target.setText("Incorrect username or password.");
        }
    }

    /**
     *
     * Clears the input fields in the login form, by
     * using the setText() method, and passing an empty string.
     *
     * @param email passing
     * @param password passing
     */
    public void Clear(TextField email, TextField password) {
        email.setText("");
        password.setText("");
    }

}
