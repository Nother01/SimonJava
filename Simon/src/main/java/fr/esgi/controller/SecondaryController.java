package fr.esgi.controller;


import java.io.IOException;
import javafx.fxml.FXML;
import fr.esgi.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}