package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class NombreJoueurControleur {
    @FXML
    private CheckBox checkBox2Players, checkBox3Players, checkBox4Players;

    @FXML
    private Button nextButton;

    private int numberOfPlayers;

    public void initialize() {
        checkBox2Players.setOnAction(event -> {
            if (checkBox2Players.isSelected()) {
                checkBox3Players.setSelected(false);
                checkBox4Players.setSelected(false);
            }
        });

        checkBox3Players.setOnAction(event -> {
            if (checkBox3Players.isSelected()) {
                checkBox2Players.setSelected(false);
                checkBox4Players.setSelected(false);
            }
        });

        checkBox4Players.setOnAction(event -> {
            if (checkBox4Players.isSelected()) {
                checkBox2Players.setSelected(false);
                checkBox3Players.setSelected(false);
            }
        });

        nextButton.setOnAction(event -> {
            if (checkBox2Players.isSelected()) {
                numberOfPlayers = 2;
            } else if (checkBox3Players.isSelected()) {
                numberOfPlayers = 3;
            } else if (checkBox4Players.isSelected()) {
                numberOfPlayers = 4;
            }

            if (numberOfPlayers != 0) {
                loadPlayerNamesScene();
            } else {
                System.out.println("Veuillez sélectionner un nombre de joueurs.");
            }
        });
    }

    private void loadPlayerNamesScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/players_name.fxml"));
            Parent root = loader.load();

            JoueurNomsControleur controller = loader.getController();
            controller.setNumberOfPlayers(numberOfPlayers);
            controller.createPlayerNameFields();

            Stage stage = (Stage) nextButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène de choix des noms des joueurs : " + e.getMessage());
        }
    }
}
