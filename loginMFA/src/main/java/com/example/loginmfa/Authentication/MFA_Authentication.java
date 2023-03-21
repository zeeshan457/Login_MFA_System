package com.example.loginmfa.Authentication;

import com.example.loginmfa.Interface.MFA_Strategy;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MFA_Authentication implements MFA_Strategy {

    /**
     * Sends the MFA code to the user's phone number and returns the code if the
     * message was successfully sent.
     * Also calling the SMS_Strategy
     * <p>
     * The method is using the Twilio API to send the SMS to the students number.
     *
     * @param phoneNumber passing user's phoneNumber
     * @param action_target display result
     */
    public String sendSMS(String phoneNumber, Text action_target) {
        // Generate a 6-digit MFA code
        String code = String.valueOf((int)(Math.random() * (999999 - 100000 + 1) + 100000));

        if (SMS_Strategy(phoneNumber)) {
            action_target.setFill(Color.RED);
            action_target.setText("Failed to send SMS MFA code.");
            // Initialize the Twilio API
            String accountSid = "AC40fb3d309ab3b483ce155e7b35a3af17";
            String authToken = "";
            Twilio.init(accountSid, authToken);

            action_target.setFill(Color.GREEN);
            action_target.setText("SMS MFA code sent.");

            // Send the MFA code via SMS
            Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber("15076666455"),
                    "2FA, Your MFA code is: " + code).create();
            if (message.getStatus().equals("queued") || message.getStatus().equals("sent")) {
                action_target.setFill(Color.GREEN);
                action_target.setText("SMS MFA code sent.");
            } else {
                action_target.setFill(Color.RED);
                action_target.setText("Failed to send SMS MFA code.");
            }

        }
        return code;
    }

    /**
     *
     * Sends the MFA code to the user's email address and returns the code if the
     * message was successfully sent.
     * Also calling the Email_Strategy
     * <p>
     * The method is using the JavaMail API to send the email over Gmail host.
     *
     * @param email passing user's email address
     * @param action_target display result
     * @return MFA code
     */
    public String sendEmail(String email, Text action_target) throws MessagingException {
        // Generate a 6-digit MFA code
        String emailCode = String.valueOf((int)(Math.random() * (999999 - 100000 + 1) + 100000));

        if (Email_Strategy(email)) {
            String from = "mymfasystem123456@gmail.com";
            String password = "ftzwsmolqcxnipmq";
            String host = "smtp.gmail.com";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(from, password);
                        }
                    });

            // Create and populate the message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("MFA Code");
            message.setText("2FA, Your MFA code is: " + emailCode);

            // Send the message
            Transport.send(message);

            // Update the action_target
            action_target.setFill(Color.GREEN);
            action_target.setText("Email MFA code sent.");
        }
        return emailCode;
    }

    @Override
    public Boolean SMS_Strategy(String phoneNumber) {
        return true;
    }

    @Override
    public Boolean Email_Strategy(String email) {
        return true;
    }
}
