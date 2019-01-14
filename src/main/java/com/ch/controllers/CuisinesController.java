package com.ch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CuisinesController extends AbstractSceneController implements Initializable {

    @FXML
    private Button startAgain;
    @FXML
    private ListView<String> equipmentProposal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Cuisines view");
    }

    @FXML
    public void handleCuisinesAction(ActionEvent actionEvent) throws IOException {
        startAgain.setDisable(true);
        nextScene(actionEvent, "start");
    }
}
