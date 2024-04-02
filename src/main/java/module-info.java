module com.myapp.main {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;

    opens com.myapp to javafx.fxml;
    exports com.myapp;
}