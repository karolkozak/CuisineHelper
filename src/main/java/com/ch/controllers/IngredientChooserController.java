package com.ch.controllers;

import com.ch.services.CuisineHelperService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IngredientChooserController extends AbstractSceneController implements Initializable {
    @Autowired
    private CuisineHelperService cuisineHelperService;

    @FXML
    private Button getResultButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Ingredient chooser view");
    }

    @FXML
    public void handleStartAction(ActionEvent actionEvent) throws IOException {
        getResultButton.setDisable(true);
        nextScene(actionEvent, "cuisines");
    }
}
