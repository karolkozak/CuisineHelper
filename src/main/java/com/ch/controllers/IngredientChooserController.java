package com.ch.controllers;

import com.ch.services.CuisineHelperService;
import com.ch.services.IngredientNodesService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class IngredientChooserController extends AbstractSceneController implements Initializable {
    @Autowired
    private CuisineHelperService cuisineHelperService;
    @Autowired
    private IngredientNodesService ingredientNodesService;

    @FXML
    private ListView<String> listOfIngredients;
    @FXML
    private ChoiceBox<String> ingredientsChoice;
    @FXML
    private Button addIngredient;
    @FXML
    private Button getResultButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Ingredient chooser view");
        List<String> ingredients = Arrays.stream(cuisineHelperService.getNetwrok().getAllNodeIds())
                .filter(nodeId -> !nodeId.equals("Kuchnia")).collect(Collectors.toList());
        ingredientsChoice.getItems().addAll(ingredients);
    }

    @FXML
    public void handleAddIngredientAction(ActionEvent actionEvent) {
        System.out.println(ingredientsChoice.getValue());
        String chosenIngredient = ingredientsChoice.getValue();
        listOfIngredients.getItems().add(chosenIngredient);
        ingredientsChoice.getItems().removeAll(chosenIngredient);
        System.out.println(ingredientsChoice.getItems().size());
        ingredientNodesService.addIngredient(chosenIngredient);
    }

    @FXML
    public void handleStartAction(ActionEvent actionEvent) throws IOException {
        getResultButton.setDisable(true);
        addIngredient.setDisable(true);
        nextScene(actionEvent, "cuisines");
    }
}
