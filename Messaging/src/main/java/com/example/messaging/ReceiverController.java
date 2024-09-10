package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.UDPConnection;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ReceiverController {

    @FXML
    private TextArea messageArea;

    @FXML
    private Label ipLabel;  // Etiqueta para la IP actual

    @FXML
    private Label portLabel;  // Etiqueta para el puerto actual

    @FXML
    public void initialize() {
        UDPConnection connection = UDPConnection.getInstance();

        // Mostrar la IP actual en la vista
        ipLabel.setText(getCurrentIPAddress());

        // Mostrar el puerto 5001 en la vista
        portLabel.setText("5001");

        // Crear un hilo para recibir mensajes de manera continua
        connection.setPort(5001);

        Thread receiverThread = new Thread(() -> {
            while (true) {
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