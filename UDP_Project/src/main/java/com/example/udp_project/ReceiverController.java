package com.example.udp_project;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.UDPConnection;

public class ReceiverController {
    @FXML
    private TextArea messageArea;

    @FXML
    private Label ipLabel;

    @FXML
    private Label portLabel;

    private UDPConnection udpConnection;
    private String lastMessage = "";

    public void initialize() {
        udpConnection = UDPConnection.getInstance();
        udpConnection.setPort(5001);

        new Thread(udpConnection).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String currentMessage = udpConnection.getLastMessage();
                if (!lastMessage.equals(currentMessage)) {
                    lastMessage = currentMessage;
                    Platform.runLater(() -> updateMessage(lastMessage));
                }
            }
        }).start();
    }

    public void updateMessage(String message) {
        messageArea.appendText(message + "\n");
    }
}
