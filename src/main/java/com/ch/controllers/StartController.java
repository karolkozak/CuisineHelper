package com.ch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class StartController extends AbstractSceneController implements Initializable {

    @FXML
    private Button startButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Start view");
    }

    @FXML
    public void handleStartAction(ActionEvent actionEvent) throws IOException {
        startButton.setDisable(true);
        nextScene(actionEvent, "ingredientChooser");
    }
}
