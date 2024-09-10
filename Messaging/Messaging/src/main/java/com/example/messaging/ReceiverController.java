package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import util.UDPConnection;

public class ReceiverController {

    @FXML
    private TextArea messageArea;

    @FXML
    public void initialize() {
        UDPConnection connection = UDPConnection.getInstance();
        connection.setPort(5001); // Configurar el puerto para recibir mensajes

        // Crear un hilo para recibir mensajes de manera continua
        Thread receiverThread = new Thread(() -> {
            while (true) {
                String receivedMessage = connection.receiveDatagram(); // Recibir mensaje usando UDPConnection
                if (receivedMessage != null) {
                    // Actualizar el TextArea en el hilo de la aplicación JavaFX
                    javafx.application.Platform.runLater(() -> messageArea.appendText(receivedMessage + "\n"));
                }
            }
        });

        receiverThread.setDaemon(true); // Hacer que el hilo termine al cerrar la aplicación
        receiverThread.start();
    }
}