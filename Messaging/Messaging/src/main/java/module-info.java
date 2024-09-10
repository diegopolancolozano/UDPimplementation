module com.example.messaging {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.messaging to javafx.fxml;
    exports com.example.messaging;
}