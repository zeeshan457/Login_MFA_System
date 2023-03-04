package com.example.loginmfa;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    /**
     * Attributes
     */
    GridPane grid = new GridPane();
    Text scenetitle = new Text("MFA Login");
    Label userName = new Label("Username:");
    Label pw = new Label("Password:");
    TextField userTextField = new TextField();
    PasswordField pwBox = new PasswordField();
    Button btn = new Button("Sign in");
    final Text actiontarget = new Text();

    /**
     * Creates a Login MFA UI for users
     *
     * @param stage to display the stage
     * @throws IOException file errors
     */
    @Override
    public void start(Stage stage) throws IOException {

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
        userName.setStyle("-fx-font-weight: bold;");
        grid.add(userName, 0, 1);
        grid.add(userTextField, 1, 1);

        // Add the password label and field
        pw.setStyle("-fx-font-weight: bold;");
        grid.add(pw, 0, 2);
        grid.add(pwBox, 1, 2);

        // Add the login button
        btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 3);


        // Add the error message text
        actiontarget.setFill(Color.RED);
        grid.add(actiontarget, 1, 5);

        // Set the action for the login button
        btn.setOnAction(e -> {
            if (userTextField.getText().equals("test") && pwBox.getText().equals("test")) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Login successful!");
            } else {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Incorrect username or password.");
            }
        });


        // Add the scene to the stage
        Scene scene = new Scene(grid, 400, 275);
        stage.setScene(scene);
        stage.setTitle("Login MFA System");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}