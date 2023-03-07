package com.example.loginmfa.Service;

import com.example.loginmfa.Authentication.SMS_MFA;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Optional;

public class LoginService {

    /**
     * Attributes
     */
    private SMS_MFA MFA;

    public LoginService(SMS_MFA mfa) {
        this.MFA = mfa;
    }


    /**
     *
     * Handles the login for the MFA system
     *
     * @param event passing
     * @param actiontarget passing
     * @param email passing
     * @param password passing
     */
    public void Login(ActionEvent event, Text actiontarget, String email, String password) {
        if (MFA.login(email, password)) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("SMS Authentication");
            dialog.setHeaderText("Enter the 6-digit code sent to your phone");
            dialog.setContentText("Code:");

            //int code = Integer.parseInt(dialog.getDefaultValue());

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent() && result.get().equals(Integer.toString(5))) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Login successful!");
            } else {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Incorrect MFA code.");
            }
        } else {
            actiontarget.setFill(Color.RED);
            actiontarget.setText("Incorrect username or password.");
        }
    }

    public void Clear(TextField email, TextField password) {
        email.setText("");
        password.setText("");
    }




}
