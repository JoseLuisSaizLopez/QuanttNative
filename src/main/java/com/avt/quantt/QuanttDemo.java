package com.avt.quantt;

import com.formdev.flatlaf.FlatDarkLaf;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class QuanttDemo extends Application {

    @Override
    public void start(Stage stage) {

        QuanttNative qNative = new QuanttNative(
                new QuanttNative.QParams()
                        .add(QuanttNative.ParamKey.TITLE_STRING, "qNativeDemo")
                        .add(QuanttNative.ParamKey.SIZE_DIMENSION, new Dimension(720, 900))
                        .add(QuanttNative.ParamKey.TITLEBAR_BACKGROUND_COLOR, new Color(0x463088))
                        .add(QuanttNative.ParamKey.TITLEBAR_FOREGROUND_COLOR, new Color(0xC4C1DA))
        );
        qNative.loadScene(HelloApplication.class.getResource("hello-view.xml"));
        qNative.start();
    }

    public static void main(String[] args) {
        launch();
    }
}