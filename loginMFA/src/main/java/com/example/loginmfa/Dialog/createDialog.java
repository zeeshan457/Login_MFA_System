package com.example.loginmfa.Dialog;

import com.example.loginmfa.Interface.Dialog_Strategy;
import javafx.scene.control.TextInputDialog;

public class createDialog implements Dialog_Strategy {

    /**
     * Create dialog box for Windows devices
     */
    public TextInputDialog createWindowsDialog() {
        TextInputDialog dialogPlatform = new TextInputDialog();
        if (Windows()) {
            dialogPlatform.setTitle("Platform Dialog");
            dialogPlatform.setHeaderText("Please choose the platform");
            dialogPlatform.setContentText("Enter 1 for Windows, 2 for Android:");

        } else {
            System.out.println("Could not create the dialog.");
        }
        return dialogPlatform;
    }

    /**
     * Create dialog box for Android devices
     */
    public TextInputDialog createAndroidDialog() {
        TextInputDialog dialogPlatform = new TextInputDialog();
        if (Android()) {
            dialogPlatform.setTitle("Platform Dialog");
            dialogPlatform.setHeaderText("Please choose the platform");
            dialogPlatform.setContentText("Enter 1 for Windows, 2 for Android:");

        } else {
            System.out.println("Could not create the dialog.");
        }
        return dialogPlatform;
    }

    /**
     * Strategies for implementation
     */
    @Override
    public Boolean Windows() {
        return true;
    }
    @Override
    public Boolean Android() {
        return true;
    }
}
