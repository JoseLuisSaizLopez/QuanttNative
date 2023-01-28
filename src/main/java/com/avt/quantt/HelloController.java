package com.avt.quantt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HelloController {

    @FXML
    private Tab welcomeTab;

    @FXML
    private Tab installTab;

    @FXML
    private GridPane container;


    private GridPane welcomePage;
    private GridPane installPage;

    @FXML
    protected void initialize() {

        //Load pages
        try {
            welcomePage = FXMLLoader.load(this.getClass().getResource("welcome-view.fxml"));
            installPage = FXMLLoader.load(this.getClass().getResource("installer-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Set tab selection properties
        welcomeTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setPage(welcomePage);
            }
        });
        installTab.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                setPage(installPage);
            }
        });

        //Set default tab as welcome tab
        welcomeTab.getTabPane().getSelectionModel().select(0);
        setPage(welcomePage);
    }

    private void setPage(Node page) {
        container.getChildren().clear();
        container.getChildren().add(page);
    }


}