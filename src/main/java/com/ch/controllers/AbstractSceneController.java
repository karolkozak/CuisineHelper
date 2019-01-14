package com.ch.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public abstract class AbstractSceneController {
    @Autowired
    ConfigurableApplicationContext context;

    protected void nextScene(ActionEvent actionEvent, String view) throws IOException {
        String viewPath = new StringBuilder()
                .append("../../../views/")
                .append(view)
                .append(".fxml")
                .toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
        loader.setControllerFactory(context::getBean);
        Parent scene = loader.load();
        Scene home_page = new Scene(scene);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.setScene(home_page);
        app_stage.show();
    }

    protected void getValueFromView() {
    }
}
