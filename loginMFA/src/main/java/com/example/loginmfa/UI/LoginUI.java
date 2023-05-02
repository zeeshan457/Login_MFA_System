package com.example.loginmfa.UI;

import com.example.loginmfa.Authentication.MFA_Authentication;
import com.example.loginmfa.Service.LoginService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.io.IOException;

import static javafx.application.Application.launch;

public class LoginUI extends Application {

    /**
     * Attributes
     */
    private ToggleGroup mfaOptions;
    private GridPane grid = new GridPane();
    private Stage stage = new Stage();
    private Text scenetitle = new Text("MFA Login");
    private Label email = new Label("Email:");
    private Label pw = new Label("Password:");
    private TextField emailField = new TextField();
    private PasswordField pwBox = new PasswordField();
    private Button Login_btn = new Button("Sign in");
    private Button Clear_btn = new Button("Clear");
    final Text actiontarget = new Text();
    private LoginService login = new LoginService();

    private RadioButton smsOption = new RadioButton("SMS");
    BorderPane borderPane = new BorderPane();

    /**
     * Constructor, injecting MFA
     *
     * @param mfa
     */
//    public LoginUI() {
//    }

    /**
     *
     * Login MFA staging method, to initialise all the components and methods in the code.
     *
     * @param stage to display the stage
     * @throws IOException file errors
     */
    @Override
    public void start(Stage stage) throws IOException {

        // Methods
        createLoginForm();
        actionEvents();

        // Add the scene
        Scene scene = new Scene(borderPane, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login MFA System");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Creates the layout and appearance of the login form
     */
    public void createLoginForm() {

        // Add a padding and a border to the BorderPane
        borderPane.setCenter(grid);
        borderPane.setPadding(new Insets(10));
        borderPane.setStyle("-fx-border-color: black; -fx-border-width: 10px;");

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

        // Add the login button
        Login_btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(Clear_btn);
        hbBtn.getChildren().add(Login_btn);
        grid.add(hbBtn, 1, 4);

        // Add the error message text
        actiontarget.setFill(Color.RED);
        grid.add(actiontarget, 1, 6);

        // Add the MFA options
        mfaOptions = new ToggleGroup();
        smsOption.setToggleGroup(mfaOptions);
        smsOption.setSelected(true);
        RadioButton emailOption = new RadioButton("Email");
        emailOption.setToggleGroup(mfaOptions);
        HBox hbMfa = new HBox(10);
        hbMfa.setAlignment(Pos.CENTER_LEFT);
        hbMfa.getChildren().addAll(new Label("MFA Method:"), smsOption, emailOption);
        grid.add(hbMfa, 0, 3, 2, 1);
    }

    /**
     * Action events for MFA system
     */
    public void actionEvents() {
        Login_btn.setOnAction(e -> {
            RadioButton selectedOption = (RadioButton) mfaOptions.getSelectedToggle();
            try {
                login.Login(e, actiontarget, selectedOption.getText(), emailField.getText(), pwBox.getText(), "+447592309062");

            } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
        });

        Clear_btn.setOnAction(e -> {
            login.Clear(emailField, pwBox);
        });
    }

    /**
     *
     * Launching application
     *
     * @param args passing
     */
    public static void main(String[] args) {
        launch();
    }
}