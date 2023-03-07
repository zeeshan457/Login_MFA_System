package com.example.loginmfa;

import com.example.loginmfa.Authentication.SMS_MFA;
import com.example.loginmfa.Service.LoginService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginUI extends Application {

    /**
     * Attributes
     */
    private ToggleGroup mfaOptions;

    private GridPane grid = new GridPane();
    private Text scenetitle = new Text("MFA Login");
    private Label email = new Label("Email:");
    private Label pw = new Label("Password:");
    private Label otpLabel = new Label("One-Time Password:");
    private TextField emailField = new TextField();
    private PasswordField pwBox = new PasswordField();
    private TextField otpField = new TextField();
    private Button Login_btn = new Button("Sign in");
    private Button Clear_btn = new Button("Clear");
    final Text actiontarget = new Text();
    private LoginService login = new LoginService(new SMS_MFA());


    /**
     * Login MFA UI
     *
     * @param stage to display the stage
     * @throws IOException file errors
     */
    @Override
    public void start(Stage stage) throws IOException {

        // Method calls
        createLoginForm();
        actionEvents();

        // Add the scene
        Scene scene = new Scene(grid, 400, 275);
        stage.setScene(scene);
        stage.setTitle("Login MFA System");
        stage.show();
    }

    /**
     * Creates the layout and appearance of the login form
     */
    public void createLoginForm() {
        // Create the login form
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #f2f2f2;");

        // Add the title
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // Add the username label and field
        email.setStyle("-fx-font-weight: bold;");
        grid.add(email, 0, 1);
        grid.add(emailField, 1, 1);

        // Add the password label and field
        pw.setStyle("-fx-font-weight: bold;");
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);

        // Add the OTP label and field
        otpLabel.setStyle("-fx-font-weight: bold;");
        grid.add(otpLabel, 0, 3);
        grid.add(otpField, 1, 3);

        // Add the login and clear button
        Login_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(Clear_btn);
        hbBtn.getChildren().add(Login_btn);
        grid.add(hbBtn, 1, 4);

        // Add the error message text
        actiontarget.setFill(Color.RED);
        grid.add(actiontarget, 1, 5);

        // Add a handler for the OTP field to limit its input to 6 digits
        otpField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,6}")) {
                otpField.setText(oldValue);
            }
        });
    }

    /**
     * Action events for MFA system
     */
    public void actionEvents() {
        Login_btn.setOnAction(e -> {
            login.Login(e, actiontarget, emailField.getText(), pwBox.getText());
        });

        Clear_btn.setOnAction(e -> {
            login.Clear(emailField, pwBox);
        });
    }

    /**
     * launching application
     * @param args passing
     */
    public static void main(String[] args) {
        launch();
    }

}