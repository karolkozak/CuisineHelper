package com.ch.controllers;

import com.ch.services.CuisineHelperService;
import com.ch.services.IngredientNodesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class CuisinesController extends AbstractSceneController implements Initializable {
    @Autowired
    private CuisineHelperService cuisineHelperService;
    @Autowired
    private IngredientNodesService ingredientNodesService;
    @FXML
    private Button startAgain;
    @FXML
    private ListView<String> suggestedCuisines;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Cuisines view");
        List<String> cuisines = cuisineHelperService.getSupportedCuisines(ingredientNodesService.getIngredientNodes());
        suggestedCuisines.getItems().addAll(cuisines);
    }

    @FXML
    public void handleCuisinesAction(ActionEvent actionEvent) throws IOException {
        startAgain.setDisable(true);
        nextScene(actionEvent, "start");
    }
}
