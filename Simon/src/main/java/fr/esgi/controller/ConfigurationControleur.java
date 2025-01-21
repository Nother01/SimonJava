package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationControleur {

    @FXML
    private Button btnSound1, btnSound2, btnSound3, btnSound4, btnSave;

    @FXML
    private Label lblSound1, lblSound2, lblSound3, lblSound4;

    @Setter
    private List<String> playerNames = new ArrayList<>();


    private final String[] soundPaths = new String[4];

    @FXML
    public void initialize() {
        btnSound1.setOnAction(event -> selectSound(0, lblSound1));
        btnSound2.setOnAction(event -> selectSound(1, lblSound2));
        btnSound3.setOnAction(event -> selectSound(2, lblSound3));
        btnSound4.setOnAction(event -> selectSound(3, lblSound4));

        btnSave.setOnAction(event -> handleNext());
    }

    private void selectSound(int buttonIndex, Label label) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Sound File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3")
        );

        Stage stage = (Stage) label.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            soundPaths[buttonIndex] = selectedFile.getAbsolutePath();
            label.setText(selectedFile.getName());
        }

    }

    public String[] getSoundPaths() {
        return soundPaths;
    }

    private void handleNext() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/pad.fxml"));
            Parent root = loader.load();

            PadController controller = loader.getController();

            controller.setPlayerNames(playerNames);
            controller.setSoundPaths(soundPaths);
            controller.setNumberOfFlashes(4);

            controller.startGame();

            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène suivante : " + e.getMessage());
        }
    }
}
