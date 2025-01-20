package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoueurNomsControleur {
    @FXML
    private GridPane playerGrid;

    @FXML
    private Button btnNext;

    @Setter
    private int numberOfPlayers;
    private final List<TextField> playerNameFields = new ArrayList<>();

    @FXML
    public void initialize() {
        btnNext.setOnAction(event -> handleNext());
    }

    public void createPlayerNameFields() {
        playerGrid.getChildren().clear();
        playerNameFields.clear();

        for (int i = 0; i < numberOfPlayers; i++) {
            Label label = new Label("Player " + (i + 1) + ":");
            TextField textField = new TextField();
            textField.setPromptText("Enter name");

            playerGrid.add(label, 0, i);
            playerGrid.add(textField, 1, i);
            playerNameFields.add(textField);
        }
    }

    private void handleNext() {
        List<String> playerNames = new ArrayList<>();
        for (TextField textField : playerNameFields) {
            String name = textField.getText().trim();
            if (name.isEmpty()) {
                textField.setStyle("-fx-border-color: red;");
                return;
            }
            playerNames.add(name);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/config.fxml"));
            Stage stage = (Stage) btnNext.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            playerNames.forEach(System.out::println);
            ConfigurationControleur configController = loader.getController(); // pass player names to the next controller
            configController.setPlayerNames(playerNames);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
