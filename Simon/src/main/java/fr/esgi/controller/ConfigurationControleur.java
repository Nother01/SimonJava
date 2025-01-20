package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ConfigurationControleur {

    @FXML
    private Button btnSound1, btnSound2, btnSound3, btnSound4, btnSave;

    @FXML
    private Label lblSound1, lblSound2, lblSound3, lblSound4;

    private final String[] soundPaths = new String[4];

    @FXML
    public void initialize() {
        btnSound1.setOnAction(event -> selectSound(0, lblSound1));
        btnSound2.setOnAction(event -> selectSound(1, lblSound2));
        btnSound3.setOnAction(event -> selectSound(2, lblSound3));
        btnSound4.setOnAction(event -> selectSound(3, lblSound4));
    }

    private void selectSound(int buttonIndex, Label label) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Sound File");
        String defaultDirectory = "src/main/resources/fr/esgi/audio";
        fileChooser.setInitialDirectory(new File(defaultDirectory));
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
}
