module com.example.udp_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.udp_project to javafx.fxml;
    exports com.example.udp_project;
}