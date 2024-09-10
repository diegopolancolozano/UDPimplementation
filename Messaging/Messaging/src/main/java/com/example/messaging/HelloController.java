package com.example.messaging;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class HelloController {

    @FXML
    private Label welcomeText;

    // Cuando se hace clic en el botón de enviar (cambia a la pantalla de envío)
    @FXML
    protected void onSendButtonClick() {
        welcomeText.setText("Switching to Sender Screen...");
        try {
            switchToSender();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cuando se hace clic en el botón de recibir (cambia a la pantalla de recepción)
    @FXML
    protected void onReceiveButtonClick() {
        welcomeText.setText("Switching to Receiver Screen...");
        try {
            switchToReceiver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cambia a la pantalla de envío
    private void switchToSender() throws IOException {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sender-view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Cambia a la pantalla de recepción
    private void switchToReceiver() throws IOException {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("receiver-view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
