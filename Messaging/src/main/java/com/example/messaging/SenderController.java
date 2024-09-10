package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.UDPConnection;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SenderController {

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField messageTextField;

    @FXML
    private TextField portArea;

    @FXML
    private Label ipLabel;  // Etiqueta para la IP actual

    @FXML
    private Label portLabel;  // Etiqueta para el puerto actual

    @FXML
    public void initialize() {
        // Obtener la IP actual del computador y mostrarla en la vista
        ipLabel.setText(getCurrentIPAddress());

        // Mostrar el puerto 5001, que es el utilizado en este caso
        portLabel.setText("5001");
    }

    @FXML
    protected void onSendButtonClick() {
        String ipAddress = ipTextField.getText();
        String message = messageTextField.getText();
        String port = portArea.getText();

        if (!ipAddress.isEmpty() && !message.isEmpty()) {
            // Usar la instancia de UDPConnection para enviar el mensaje
            UDPConnection udpConnection = UDPConnection.getInstance();
            udpConnection.sendDatagram(message, ipAddress, 5001); // El puerto utilizado es 5001

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

    // Método para obtener la IP actual del computador
    private String getCurrentIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "127.0.0.1"; // En caso de error, retornar la IP local
        }
    }
}