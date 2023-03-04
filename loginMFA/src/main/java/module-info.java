module com.example.loginmfa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.loginmfa to javafx.fxml;
    exports com.example.loginmfa;
}