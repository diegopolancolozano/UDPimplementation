package com.example.udp_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.UDPConnection;

public class SenderController {
    @FXML
    private TextField ipTextField;

    @FXML
    private TextField messageTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Label ipLabel;

    @FXML
    private Label portLabel;

    private UDPConnection udpConnection;

    public void initialize() {
        udpConnection = UDPConnection.getInstance();
        udpConnection.setPort(5001);
    }

    @FXML
    private void sendMessage() {
        String ip = ipTextField.getText();
        String message = messageTextField.getText();
        int port = Integer.parseInt(portTextField.getText());

        udpConnection.setPort(port);

        udpConnection.sendDatagram(message, ip, port);
    }

    @FXML
    private void updateLabels() {
        String ip = ipTextField.getText();
        String port = portTextField.getText();
        ipLabel.setText("IP: " + ip);
        portLabel.setText("Port: " + port);
    }
}
