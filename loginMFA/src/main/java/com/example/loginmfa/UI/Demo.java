package com.example.loginmfa.UI;

import javafx.application.Application;

/**
 *
 * This system allows UoB students to log in and authenticate their login using MFA options like SMS or email. Having
 * MFA authentication will prevent unauthorised access to students accounts.
 * <p>
 * The system uses strategy pattern to quickly add MFA authentication, and the system is using a 3 tier architecture to
 * separate the layers.
 * SMS Authentication is using twilio API and Email Authentication is using JavaMail API.
 * <p>
 * To create a platform/MFA dialog, firstly add one as an interface, and then implement in the createDialog class.
 * <p>
 * To use the dialog, select the platform and then enter the OTP code and hit OK. This will be authenticated by the system.
 *
 */
public class Demo {

    public static void main(String[] args) {
        Application.launch(LoginUI.class, args);
    }
}
