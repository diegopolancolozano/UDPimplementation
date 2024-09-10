package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.UDPConnection;

public class ReceiverController {

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField portArea;

    @FXML
    public void initialize() {
        UDPConnection connection = UDPConnection.getInstance();

        // Crear un hilo para recibir mensajes de manera continua

        connection.setPort(5001);

        Thread receiverThread = new Thread(() -> {
            while (true) {
                try {
                    int port = Integer.parseInt(portArea.getText());
                    connection.setPort(port);
                }catch (Exception e){

                }
                String receivedMessage = connection.receiveDatagram(); // Recibir mensaje usando UDPConnection
                if (receivedMessage != null) {
                    // Actualizar el TextArea en el hilo de la aplicación JavaFX
                    javafx.application.Platform.runLater(() -> messageArea.appendText(receivedMessage + "\n"));
                    System.out.println(receivedMessage);
                }
            }
        });

        receiverThread.setDaemon(true); // Hacer que el hilo termine al cerrar la aplicación
        receiverThread.start();
    }
}