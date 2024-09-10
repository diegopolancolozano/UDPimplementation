package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import util.*;

import java.io.IOException;

public class SenderController {

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField messageTextField;

    @FXML
    protected void onSendButtonClick() {
        String ipAddress = ipTextField.getText();
        String message = messageTextField.getText();

        if (!ipAddress.isEmpty() && !message.isEmpty()) {
            // Usar la instancia de UDPConnection para enviar el mensaje
            UDPConnection udpConnection = UDPConnection.getInstance();
            udpConnection.setPort(5001);
            udpConnection.sendDatagram(message, ipAddress, 5001); // Ajustar puerto según sea necesario

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Sent");
            alert.setHeaderText(null);
            alert.setContentText("Message sent successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText(null);
            alert.setContentText("Please enter both IP address and message.");
            alert.showAndWait();
        }
    }

}