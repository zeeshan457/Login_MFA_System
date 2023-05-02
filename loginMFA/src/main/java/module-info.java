module com.example.loginmfa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires junit;
    requires twilio;
    requires javax.mail;
    requires javafx.web;

    opens com.example.loginmfa to javafx.fxml;
    exports com.example.loginmfa.UI;
    exports com.example.loginmfa.Service to junit, javafx.fxml;
    opens com.example.loginmfa.UI to javafx.fxml;

    exports Tests to junit, javafx.fxml;
}