package com.avt.quantt;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class QuanttDemo extends Application {

    @Override
    public void start(Stage stage) {

        //Load QNative
        QuanttNative qNative = new QuanttNative(
                //Title params
                new QuanttNative.QParams()
                        .add(QuanttNative.ParamKey.TITLE_STRING, "qNativeDemo")
                        .add(QuanttNative.ParamKey.SIZE_DIMENSION, new Dimension(720, 600))
                        .add(QuanttNative.ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0x29A4C0))
                        .add(QuanttNative.ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xECECEC)),

                //Menu composer
                new QuanttNative.QTitleMenu()
                        .addMenu("File", new QuanttNative.Option[]{
                                new QuanttNative.Option("Create new project"),
                                new QuanttNative.Option("Open project"),
                                new QuanttNative.Option("Close project", false),    //Disabled
                                new QuanttNative.Option("Save", false), //Disabled
                                new QuanttNative.Option("Save as...", false),   //Disabled
                                new QuanttNative.Option("Exit")
                        })
                        .addMenu("Edit", new QuanttNative.Option[]{
                                new QuanttNative.Option("Undo last", false),    //Disabled
                                new QuanttNative.Option("Redo last", false),    //Disabled
                                new QuanttNative.Option("Change color...", false),  //Disabled
                                new QuanttNative.Option("Edit configurations...", false)    //Disabled
                        })
                        .addMenu("Help", null)
                        .addMenuOptionListener((key, option) -> {
                            switch (option) {
                                case "Exit": System.exit(0); break;
                                default: System.out.println(option); break;
                            }
                        })
        );

        //Set Javafx scene
        qNative.loadScene(this.getClass().getResource("hello-view.fxml"));

        //Run application
        qNative.start();
    }

    public static void main(String[] args) {
        launch();
    }
}